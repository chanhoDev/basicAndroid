package com.chanho.basic.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.chanho.basic.R
import com.chanho.basic.databinding.FragmentDetailBinding
import com.chanho.basic.model.Movie
import com.chanho.basic.ui.home.HomeListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var movieParam:Movie
    private val viewModel:DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            movieParam = it.getParcelable(MOVIE_PARAM)?:Movie()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_detail,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.model = movieParam
        onObserve()
        return binding.root
    }
    private fun onObserve(){
        with(viewModel){
            onBackClickEvent.observe(viewLifecycleOwner){
               requireActivity().onBackPressed()
            }
        }
    }

    companion object {
        const val MOVIE_PARAM = "movie_param"
        @JvmStatic
        fun newInstance(movie: Movie) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_PARAM, movie)
                }
            }
    }
}