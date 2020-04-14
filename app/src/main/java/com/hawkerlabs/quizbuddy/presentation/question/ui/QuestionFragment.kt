package com.hawkerlabs.quizbuddy.presentation.question.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hawkerlabs.quizbuddy.R
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import com.hawkerlabs.quizbuddy.databinding.QuestionFragmentBinding
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.QuestionViewModel
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.question_fragment.*
import javax.inject.Inject


/**
 * https://android--code.blogspot.com/2018/02/android-kotlin-radiogroup-and.html
 */
class QuestionFragment : DaggerFragment(){

//    private lateinit var optionsAdapter: OptionsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var sessionViewModel: SessionViewModel

    private lateinit var binding: QuestionFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_fragment, container, false)



        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initUi()
        subscribeUi()

    }

    private fun initUi() {
//        optionsAdapter = OptionsAdapter()
//        binding.optionsGroup.orientation = LinearLayout.HORIZONTAL;
//        val rbn = RadioButton(activity)
//        val rbn2 = RadioButton(activity)
//        rbn.id = View.generateViewId()
//        rbn.text = "it.text"
//
//        rbn2.id = View.generateViewId()
//        rbn2.text = "it.text"
//
//       var  params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
//        binding.optionsGroup.layoutParams = params
//        binding.optionsGroup.addView(rbn)
//        binding.optionsGroup.addView(rbn2)

        submitAnswer.setOnClickListener{
            questionViewModel.onSubmit()
            questionViewModel.onNext()
        }
    }


    /**
     *
     */
    private fun  subscribeUi(){

        questionViewModel.getQuestion.observe(viewLifecycleOwner, Observer{question->
            binding.questionText.text = question.questionText

            binding.optionsGroup.removeAllViews()
            question.options.map {
                val rbn = RadioButton(activity)
                rbn.id = it.id
                rbn.text = it.text
                rbn.typeface = Typeface.create("roboto_medium", Typeface.NORMAL)


                binding.optionsGroup.addView(rbn)
            }
//            binding.optionsList.adapter = optionsAdapter
//            optionsAdapter.setOptions(question.options)


            binding.optionsGroup.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { _, checkedId ->

                    questionViewModel.onOptionSelect(checkedId)

                })

        })


        questionViewModel.isFinishTest.observe(viewLifecycleOwner ,Observer{
            if(it){

                Navigation.findNavController(binding.root).navigate(R.id.resultsFragment)
            }
        }

        )

    }

    private fun initViewModels() {

        activity?.let {
            questionViewModel = ViewModelProvider(it, viewModelFactory).get(QuestionViewModel::class.java)
            sessionViewModel = ViewModelProvider(it, viewModelFactory).get(SessionViewModel::class.java)



        }


    }

}