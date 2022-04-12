package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ResultQuizBinding


class ResultFragment : Fragment() {
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

        binding.quizResultText.text = "Ваш результат ${resultQuiz(currentUserAnswers)}%"

        binding.imageRestart.setOnClickListener {
            onResultFragmentListener?.restartQuiz()
        }

        binding.imageClose.setOnClickListener {
            activity?.finishAffinity()
        }

        binding.imageShare.setOnClickListener {
            val resultIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareResult(resultQuiz(currentUserAnswers),currentUserAnswers))
                putExtra(Intent.EXTRA_SUBJECT, "Quiz result")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(resultIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun resultQuiz(userAnswers: IntArray): Int {
        var result = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == DataBase.questionList[i].answer) {
                result++
            }
        }
        return result * 100 / userAnswers.size
    }

    private fun shareResult(resultQuiz: Int,userAnswers: IntArray): String {
        var shareResultText =" Результат $resultQuiz % \n\n "
        for (i in userAnswers.indices) {
            shareResultText += "Вопрос N${i+1}: ${DataBase.questionList[i].question} \n"
            when(userAnswers[i]){
                1 -> shareResultText+= "Ваш ответ: ${DataBase.questionList[i].option1} \n\n"
                2 -> shareResultText+= "Ваш ответ: ${DataBase.questionList[i].option2} \n\n"
                3 -> shareResultText+= "Ваш ответ: ${DataBase.questionList[i].option3} \n\n"
                4 -> shareResultText+= "Ваш ответ: ${DataBase.questionList[i].option4} \n\n"
                5 -> shareResultText+= "Ваш ответ: ${DataBase.questionList[i].option5} \n\n"
            }
        }
        return shareResultText
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