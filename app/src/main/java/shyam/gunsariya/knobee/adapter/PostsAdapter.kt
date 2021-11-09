package shyam.gunsariya.knobee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray
import shyam.gunsariya.knobee.R
import shyam.gunsariya.knobee.model.DummyModel

class PostsAdapter(
    private val context: Context,
    private val list: List<DummyModel>
) : RecyclerView.Adapter<PostsAdapter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(
                    R.layout.item_post,
                    parent,
                    false)
        )
    }

    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {

        val galArray = JSONArray(list[position].gallery)
        val listLen = galArray.length()-1

        when {
            list[position].positionNo == 1 -> {
                holder.image2.visibility = View.GONE
                holder.image3.visibility = View.GONE
                holder.imageCount.visibility = View.GONE
                holder.image4.visibility = View.GONE
            }
            list[position].positionNo == 2 -> {
                holder.image3.visibility = View.GONE
                holder.image4.visibility = View.GONE
                holder.imageCount.visibility = View.GONE
            }
            list[position].positionNo == 3 -> {
                holder.image4.visibility = View.GONE
                holder.imageCount.visibility = View.GONE
            }
            list[position].positionNo == 4 -> {
                holder.imageCount.visibility = View.GONE
            }
            list[position].positionNo!! > 4 -> {
                holder.imageCount.visibility = View.VISIBLE
                val count = list[position].positionNo?.minus(4)

                "+$count more images".also { holder.imageCount.text = it }
            }
        }

        for (j in 0..listLen) {
            val n = galArray.getJSONObject(j)

            val path = n.getString("filename")

            when (j) {
                0 -> {
                    Picasso.get().load(path).into(holder.image1)
                }
                1 -> {
                    Picasso.get().load(path).into(holder.image2)
                }
                2 -> {
                    Picasso.get().load(path).into(holder.image3)
                }
                3 -> {
                    Picasso.get().load(path).into(holder.image4)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image1: ImageView = itemView.findViewById(R.id.image1)
        var image2: ImageView = itemView.findViewById(R.id.image2)
        var image3: ImageView = itemView.findViewById(R.id.image3)
        var image4: ImageView = itemView.findViewById(R.id.image4)
        var imageCount: TextView = itemView.findViewById(R.id.more_image_text)
    }

}