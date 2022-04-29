package com.bisa.submissionone.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.IMAGE_URL
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ToastCustom {


    companion object{


        fun toastIconError(context: Activity, layoutInflater: LayoutInflater) {
            val toast = Toast(context)
            toast.duration = Toast.LENGTH_LONG

            //inflate view
            val custom_view = layoutInflater.inflate(R.layout.toast_icon_text, null,false)
            (custom_view.findViewById<View>(R.id.message) as TextView).text = "This is Error Message"
            (custom_view.findViewById<View>(R.id.icon) as ImageView).setImageResource(R.drawable.ic_close)
            (custom_view.findViewById<View>(R.id.parent_view_toast_text) as CardView).setCardBackgroundColor(context.resources.getColor(R.color.red))
            toast.setView(custom_view)
            toast.show()
        }

        fun toastFloatingImageMovie(context: Activity, layoutInflater: LayoutInflater, movie: CatalogMovieEntity, state: Boolean) {
            val toast = Toast(context)
            toast.duration = Toast.LENGTH_LONG

            //inflate view
            val custom_view: View = layoutInflater.inflate(R.layout.toast_item_image, null, false)
            Glide.with(context)
                    .load(IMAGE_URL + movie.imagePoster)
                    .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(custom_view.findViewById<View>(R.id.img_catalog_toast_image) as ImageView)
            (custom_view.findViewById<View>(R.id.tv_title_catalog_toast_image) as TextView).text = movie.title

            if (state){
                (custom_view.findViewById<View>(R.id.tv_add_favorite_toast_image) as TextView).text = "Added to Favorite"
            }else{

                (custom_view.findViewById<View>(R.id.tv_add_favorite_toast_image) as TextView).text = "Deleted Favorite"
            }

            toast.setView(custom_view)
            toast.show()
        }

        fun toastFloatingImageTvShow(context: Activity, layoutInflater: LayoutInflater, tv: CatalogTvEntity, state: Boolean) {
            val toast = Toast(context)
            toast.duration = Toast.LENGTH_LONG

            //inflate view
            val custom_view: View = layoutInflater.inflate(R.layout.toast_item_image, null)
            Glide.with(context)
                    .load(IMAGE_URL + tv.imagePoster)
                    .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error))
                    .into(custom_view.findViewById<View>(R.id.img_catalog_toast_image) as ImageView)

            (custom_view.findViewById<View>(R.id.tv_title_catalog_toast_image) as TextView).text = tv.title

            if (state){
                (custom_view.findViewById<View>(R.id.tv_add_favorite_toast_image) as TextView).text = "Added to Favorite"
            }else{

                (custom_view.findViewById<View>(R.id.tv_add_favorite_toast_image) as TextView).text = "Deleted from Favorite"
            }

            toast.setView(custom_view)
            toast.show()
        }
    }


}