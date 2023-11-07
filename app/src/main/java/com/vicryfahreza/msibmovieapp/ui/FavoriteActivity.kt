package com.vicryfahreza.msibmovieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.analytics.FirebaseAnalytics  // Import Firebase Analytics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vicryfahreza.msibmovieapp.databinding.ActivityFavoriteBinding
import com.vicryfahreza.msibmovieapp.model.Favorite
import com.vicryfahreza.msibmovieapp.ui.adapter.FavoriteAdapter
import com.vicryfahreza.msibmovieapp.ui.adapter.FavoriteItemListener
import com.xwray.groupie.GroupieAdapter

class FavoriteActivity : AppCompatActivity(), FavoriteItemListener {

    private val db  = Firebase.firestore
    private val listFavorite = mutableListOf<Favorite>()
    private val adapter = GroupieAdapter()
    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics  // Inisialisasi Firebase Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val rvFavorite = binding.rvFavorite
        rvFavorite.layoutManager = LinearLayoutManager(this)
        rvFavorite.adapter = adapter

        val currentUser = Firebase.auth.currentUser

        val dataRef = db.collection(PATH_FAVORITES).document(currentUser?.uid ?: "")
            .collection(PATH_USER_FAVORITES)
            .orderBy("createdAt")

        dataRef.addSnapshotListener{ value, _ ->
            val documents = value?.documents
            documents?.let {
                listFavorite.clear()
                adapter.clear()
                it.map { queryDoc ->
                    Log.d(TAG, "result : ${queryDoc.data.toString()}")
                    val timeStamp = queryDoc.data!!["createdAt"] as Timestamp
                    listFavorite.add(
                        Favorite(
                            id = queryDoc.id,
                            url = queryDoc.data!!["url"].toString(),
                            title = queryDoc.data!!["title"].toString(),
                            description = queryDoc.data!!["description"].toString(),
                            createdAt = timeStamp.toDate()
                        )
                    )
                }
                listFavorite.map { fav ->
                    Log.d(TAG, "test firestore : $fav")
                    adapter.add(FavoriteAdapter(fav, this))
                }
            }

            // Pelacakan kejadian "favorite_activity_opened"
            logEvent("favorite_activity_opened")
        }
    }

    // Fungsi untuk melakukan pelacakan kejadian
    private fun logEvent(eventName: String) {
        val bundle = Bundle()
        bundle.putString("event_name", eventName)
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    companion object {
        const val PATH_FAVORITES = "favorites"
        const val PATH_USER_FAVORITES = "user_favorites"
        const val TAG = "FavoriteActivity"
    }

    override fun onItemDeleted(docId: String?) {
        docId?.let {
            Log.d(TAG, "Delete Data With Id : $docId")
            val currentUser = Firebase.auth.currentUser
            db.collection(PATH_FAVORITES)
                .document(currentUser?.uid ?: "")
                .collection(PATH_USER_FAVORITES)
                .document(docId)
                .delete()
                .addOnSuccessListener {
                    Log.d("tag", "Success Deleting Data With Id : $docId")
                }
                .addOnFailureListener {
                    Log.e("tag", "Failed Deleting data ${it.localizedMessage}")
                }
        }
    }
}
