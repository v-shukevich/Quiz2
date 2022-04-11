package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = requireNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
}