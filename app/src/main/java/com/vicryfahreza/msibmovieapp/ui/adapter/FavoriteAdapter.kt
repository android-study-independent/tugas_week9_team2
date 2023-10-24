package com.vicryfahreza.msibmovieapp.ui.adapter

import android.view.View
import com.squareup.picasso.Picasso
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.databinding.ItemFavoriteBinding
import com.vicryfahreza.msibmovieapp.model.Favorite
import com.xwray.groupie.viewbinding.BindableItem

interface FavoriteItemListener {
    fun onItemDeleted(docId: String?)
}

class FavoriteAdapter(
    private val favorite: Favorite,
    private val listener: FavoriteItemListener
    ) : BindableItem<ItemFavoriteBinding>(){
    override fun bind(viewBinding: ItemFavoriteBinding, position: Int) {
        viewBinding.tvTitle.text = favorite.title
        viewBinding.tvDescription.text = favorite.description
        val ivMovie = viewBinding.ivMovie
        Picasso.get().load(favorite.url).into(ivMovie)

        viewBinding.btnDelete.setOnClickListener {
            listener.onItemDeleted(favorite.id)
        }

    }

    override fun getLayout(): Int = R.layout.item_favorite

    override fun initializeViewBinding(view: View)
     : ItemFavoriteBinding = ItemFavoriteBinding.bind(view)
}