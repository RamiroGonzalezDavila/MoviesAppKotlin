package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.material3.Text
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.moviesapp.models.Movie
import kotlinx.android.synthetic.main.fragment_fragmento2.specific_movie_title
import kotlinx.android.synthetic.main.movies_item.view.movie_poster
import java.util.prefs.BackingStoreException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val TITLE_BUNDLE = "param1"
const val VOTEAVG_BUNDLE = "param2"
const val OVERVIEW_BUNDLE = "param3"
const val BACKDROP_BUNDLE = "param4"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragmento2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmento2 : android.app.Fragment() {
    // TODO: Rename and change types of parameters
    var title: String? = null
    var voteavg: String? = null
    var overview: String? = null
    var backdrop: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE_BUNDLE)
            voteavg = it.getString(VOTEAVG_BUNDLE)
            overview = it.getString(OVERVIEW_BUNDLE)
            backdrop = it.getString(BACKDROP_BUNDLE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragmento2, container, false)
        val backBut : Button = view.findViewById(R.id.button)
        backBut.setOnClickListener {
            replaceFragment(Fragmento1())
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val tit:TextView = view!!.findViewById(R.id.specific_movie_title)
        tit.setText(title)
        val vote:TextView = view!!.findViewById(R.id.specific_movie_voteavg)
        vote.setText(voteavg)
        val overv:TextView = view!!.findViewById(R.id.specific_movie_overview)
        overv.setText(overview)
        val backd:ImageView = view!!.findViewById(R.id.specific_movie_backdrop_path)
        Glide.with(backd).load(IMAGE_BASE+backdrop).into(backd)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragemento2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(title: String, voteavg: String) =
            Fragmento2().apply {
                arguments = Bundle().apply {
                    putString(TITLE_BUNDLE, title)
                    putString(VOTEAVG_BUNDLE, voteavg)
                    putString(OVERVIEW_BUNDLE, overview)
                    putString(BACKDROP_BUNDLE, backdrop)
                }
            }
    }
    private fun replaceFragment(Fragment: android.app.Fragment) {

        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContainer,Fragment)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }
}