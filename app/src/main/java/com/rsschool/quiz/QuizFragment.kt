package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var onQuizFragmentSendDataListener: OnQuizFragmentSendData



    override fun onAttach(context: Context) {
        super.onAttach(context)
        onQuizFragmentSendDataListener = context as OnQuizFragmentSendData
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changeTheme(arguments?.getInt(FRAGMENT_NUMBER) ?: 0)
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentFragmentNumber = arguments?.getInt(FRAGMENT_NUMBER) ?: 0

        binding.toolbar.title ="текущий фрагмент $currentFragmentNumber"
        binding.nextButton.setOnClickListener {
            currentFragmentNumber++
            onQuizFragmentSendDataListener.replaceFragment(currentFragmentNumber)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun changeTheme(fragmentNumber: Int) {
        val id = when (fragmentNumber) {
            0 -> R.style.Theme_Quiz_First
            1 -> R.style.Theme_Quiz_Second
            2 -> R.style.Theme_Quiz_Third
            4 -> R.style.Theme_Quiz_Fourth
            else -> R.style.Theme_Quiz_Fifth
        }
        context?.setTheme(id)

        val idSB= when (fragmentNumber) {
            0 ->  R.color.deep_orange_100_dark
            1 -> R.color.yellow_100_dark
            2 -> R.color.light_green_100_dark
            4 -> R.color.cyan_100_dark
            else -> R.color.deep_purple_100_dark
        }
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(),idSB)

    }


    companion object {
        fun newInstance(fragmentNumber: Int): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt(FRAGMENT_NUMBER,fragmentNumber)
            fragment.arguments = args
            return fragment
        }
        private const val FRAGMENT_NUMBER = "FRAGMENT_NUMBER"
    }

    interface OnQuizFragmentSendData {
        fun replaceFragment(fragmentNumber: Int)
    }
}