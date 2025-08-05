package ca.georgiancollege.movieui.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.movieui.Adapters.MovieAdapter
import ca.georgiancollege.movieui.databinding.ActivityMovieListBinding
import ca.georgiancollege.movieui.Model.Movie
import ca.georgiancollege.movieui.Utils.FirebaseUtils
import ca.georgiancollege.movieui.viewmodel.MovieViewModel

class MovieListActivity : AppCompatActivity(), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView setup
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(this, mutableListOf(), this)
        binding.rvMovies.adapter = adapter

        // Initialize ViewModel and observe data
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.movies.observe(this) { movies ->
            adapter.updateMovies(movies)
        }
    }
}
