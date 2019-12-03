package com.kanghara.riiid


import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.kanghara.riiid.databinding.FragmentEditBinding
import com.kanghara.riiidproject.domain.entities.PatchPost
import kotlinx.android.synthetic.main.fragment_edit.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class EditFragment : Fragment() {

    val detailViewModel: DetailViewModel by sharedViewModel()
    val args: EditFragmentArgs by navArgs()
    lateinit var binding: FragmentEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.edited.observe(this, Observer {
            if (it) {
                detailViewModel.resetEdited()
                Snackbar.make(
                    view!!,
                    "Successfully updated a post",
                    BaseTransientBottomBar.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        binding = FragmentEditBinding.inflate(LayoutInflater.from(context), container, false)
        binding.post = PatchPost(
            args.postTitle,
            args.postBody
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postTitleLayout.transitionName = "postTitle${args.postId}"
        postBodyLayout.transitionName = "postBody${args.postId}"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                detailViewModel.editPost(
                    args.postId,
                    PatchPost(
                        title = binding.postTitleInput.text.toString(),
                        body = binding.postBodyInput.text.toString()
                    )
                )
            }
            else -> {
            }
        }
        return true
    }


}
