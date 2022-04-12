package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ResultQuizBinding


class ResultFragment : Fragment(){
    private var _binding: ResultQuizBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var onResultFragmentListener: OnResultFragment? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
            onResultFragmentListener = context as OnResultFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResultQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.quizResultText.text = "Ваш результат  "

        binding.imageRestart.setOnClickListener {
            onResultFragmentListener?.restartQuiz()
        }
        binding.imageClose.setOnClickListener {
            activity?.finishAffinity()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }


    }


    interface OnResultFragment {
        fun restartQuiz()
    }


}