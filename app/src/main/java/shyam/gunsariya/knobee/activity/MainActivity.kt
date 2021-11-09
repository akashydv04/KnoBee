package shyam.gunsariya.knobee.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import shyam.gunsariya.knobee.R
import shyam.gunsariya.knobee.adapter.PostsAdapter
import shyam.gunsariya.knobee.model.DummyModel

class MainActivity : AppCompatActivity() {

    private var  listNew = ArrayList<DummyModel>()
    private lateinit var recyclerView : RecyclerView
    private var requestQueue: RequestQueue? = null
    private lateinit var adapter : PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.posts_recycler)

        adapter = PostsAdapter(this, listNew)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        requestQueue = Volley.newRequestQueue(this)
        getPosts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPosts() {
            val url = "https://stg.knobee.app/dummyApi.json"
            val request = JsonObjectRequest(Request.Method.POST, url, null, {
                    response ->try {
                val dataObj = response.getJSONObject("data")
                val galleryArray = dataObj.getJSONArray("Gallery")
                val len = galleryArray.length()
                (0 until len).forEach {
                    val listOfObject = galleryArray.getJSONArray(it)
                    val dummy = DummyModel()
                    dummy.setPosts(listOfObject.length(),listOfObject.toString())
                    if (listOfObject.length() != 0){
                        listNew.add(dummy)
                    }
                }
                adapter.notifyDataSetChanged()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            }, { error -> error.printStackTrace() })
            requestQueue?.add(request)
    }
}