package com.example.nytapp.listModule.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nytapp.R
import com.example.nytapp.adapters.BaseRecyclerAdapter
import com.example.nytapp.core.Constants
import com.example.nytapp.core.di.DIHandler
import com.example.nytapp.core.networking.DataResult
import com.example.nytapp.dataBase.StoryItem
import com.example.nytapp.listModule.di.ContainerComponent
import com.example.nytapp.listModule.viewModel.ContainerViewModel
import com.example.nytapp.utils.visibilityToggle
import com.example.nytapp.webViewModule.WebViewActivity
import kotlinx.android.synthetic.main.fragment_container.view.*
import javax.inject.Inject

class TopStoriesFragment : Fragment() {

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"
        fun newInstance(sectionNumber: Int): TopStoriesFragment {
            val fragment = TopStoriesFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var adapter: BaseRecyclerAdapter

    private val containerComponent: ContainerComponent by lazy { DIHandler.getContainerComponent() }
    private val viewModel: ContainerViewModel by lazy { ViewModelProviders.of(this).get(ContainerViewModel::class.java) }
    lateinit var items: List<StoryItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_container, container, false)
        containerComponent.inject(this)

        viewModel.getResults(arguments!!.getInt(ARG_SECTION_NUMBER))
        items = listOf()
        view.rvResultList.layoutManager = LinearLayoutManager(activity)
        if (arguments!!.getInt(ARG_SECTION_NUMBER) == 4)
            adapter.layoutId = R.layout.row_three_items_layout
        else
            adapter.layoutId = R.layout.row_avatar_two_items
        view.rvResultList.adapter = adapter
        adapter.items = items
        observerData()
        view.progress.visibilityToggle(true)
        adapter.onCustomClickItemListner = { v, i -> onCustomClick(v, i) }
        return view
    }

    /*
    * Observable block that updates the recylerview when data is received, Progress visibility logic also handled here
    * */
    private fun observerData() {
        viewModel.postDataRepository.observe(this, Observer<DataResult<List<StoryItem>>> { result ->
            when (result) {
                is DataResult.Progress -> view?.progress?.visibilityToggle(result.loading) /* Extension function to toggle visibility */
                is DataResult.Success -> {
                    items = result.data
                    Log.i("Sanoop", "Items -> ${items}")
                    adapter.items = items
                    adapter.notifyDataSetChanged()
                }
                is DataResult.Failure -> {
                    result.e.printStackTrace()
                    Toast.makeText(context, "No Internet.", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    /*
    * On click of any item from list view
    * */
    fun onCustomClick(view: View, position: Int) {
        val model = items[position]
        when (model.type) {
            Constants.MOVIE,
            Constants.BUSINESS,
            Constants.TOP_STORY -> {
                val intent = Intent(activity, WebViewActivity::class.java)
                val bundle = Bundle()
                bundle.putString("url", model.url)
                intent.putExtra("Web", bundle)
                activity?.startActivity(intent)
            }
            else -> {
            }
        }

    }
}