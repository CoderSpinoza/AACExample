package com.kanghara.riiid


import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.kanghara.riiid.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    val detailViewModel: DetailViewModel by viewModel()
    private lateinit var commentsAdapter: CommentsAdapter
    val args: DetailFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailBinding

    private fun showSnackBar(message: String) {
        Snackbar.make(view!!, message, BaseTransientBottomBar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.post.observe(this, Observer {
            if (it == null) {
                showSnackBar("Successfully deleted a post.")
                findNavController().navigateUp()
            } else {
                binding.postTitle = it.title
                binding.postBody = it.body
            }

        })
        detailViewModel.comments.observe(this, Observer {
            commentsAdapter.comments = it
            commentsAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        binding = FragmentDetailBinding.inflate(LayoutInflater.from(context), container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentsAdapter = CommentsAdapter(listOf())
        commentsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = commentsAdapter
        }
        postTitleView.transitionName = "postTitle${args.postId}"
        postBodyView.transitionName = "postBody${args.postId}"
        detailViewModel.fetchPostDetail(args.postId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit -> {
                val action = DetailFragmentDirections.showEdit(
                    args.postId,
                    postTitleView.text.toString(),
                    postBodyView.text.toString()
                )
                val extras = FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.postTitleView, binding.postTitleView.transitionName)
                    .addSharedElement(binding.postBodyView, binding.postBodyView.transitionName)
                    .build()
                findNavController().navigate(action, extras)
                return true
            }
            R.id.menu_delete -> {
                showDeleteDialog()
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDeleteDialog() {
        MaterialAlertDialogBuilder(context)
            .setTitle("Delete post")
            .setMessage("Are you sure you want to delete this post?")
            .setPositiveButton("Ok")
            { dialog, which -> detailViewModel.deletePost(args.postId) }
            .setNegativeButton("Cancel", null)
            .show()
    }

}
