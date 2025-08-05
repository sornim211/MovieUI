package ca.georgiancollege.movieui.Utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtils {

    // Lazy-initialized Firebase Auth instance
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    // Lazy-initialized Firestore instance
    val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    // Optional: get current user's UID
    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }
}