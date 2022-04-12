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

        val currentUserAnswers =
            arguments?.getIntArray(USER_ANSWERS) ?: IntArray(DataBase.questionList.size)

        binding.quizResultText.text = "Ваш результат ${resultQuiz(currentUserAnswers)}% ${currentUserAnswers.contentToString()}"

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

    private fun resultQuiz(userAnswers: IntArray): Int{
        var result = 0
        for (i in userAnswers.indices){
            if(userAnswers[i] == DataBase.questionList[i].answer){
                result++
            }
        }
        return result*100 / userAnswers.size
    }

    companion object {
        fun newInstance(userAnswers: IntArray): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putIntArray(USER_ANSWERS, userAnswers)
            fragment.arguments = args
            return fragment
        }
        private const val USER_ANSWERS = "USER_ANSWERS"

    }


    interface OnResultFragment {
        fun restartQuiz()
    }


}