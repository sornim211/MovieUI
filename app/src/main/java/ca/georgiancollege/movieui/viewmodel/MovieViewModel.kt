package ca.georgiancollege.movieui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.georgiancollege.movieui.Model.Movie
import ca.georgiancollege.movieui.Utils.FirebaseUtils
import com.google.firebase.firestore.ListenerRegistration

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private var listenerRegistration: ListenerRegistration? = null

    init {
        loadMovies()
    }

    private fun loadMovies() {
        listenerRegistration = FirebaseUtils.getFirestore()
            .collection("movies")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // Handle error, maybe log or set empty list
                    _movies.value = emptyList()
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val movieList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Movie::class.java)?.apply {
                            id = doc.id // assuming your Movie class has an id var
                        }
                    }
                    _movies.value = movieList
                } else {
                    _movies.value = emptyList()
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}
