package com.focalu.animation

import android.animation.*
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.focalu.animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bigHeartAnimatorSet by lazy {
        AnimatorSet().apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    binding.imgHeart.visibility = View.VISIBLE
                    binding.btnLikePressed.visibility = View.VISIBLE
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setupUI()
        setContentView(view)
    }

    private fun setupUI() {
        loadImages()
        binding.imgPost.setOnClickListener {
            //animateRes()
            animateCode()
        }
    }

    private fun scaleXY(
        view: View,
        from: Float,
        to: Float,
        duration: Long,
        interpolator: Interpolator
    ): AnimatorSet {
        val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, from, to).apply {
            this.interpolator = interpolator
            this.duration = duration
        }
        val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, from, to).apply {
            this.duration = duration
            this.interpolator = interpolator
        }

        return AnimatorSet().apply { playTogether(scaleX, scaleY) }
    }

    private fun animateCode() {

        //Agrandar
        val bigHeartAnim1 = scaleXY(binding.imgHeart, 0f, 2f, 200, AccelerateInterpolator())
        //Achicar un poco
        val bigHeartAnim2 = scaleXY(binding.imgHeart, 2f, 1.7f, 50, DecelerateInterpolator())
        //Achicar hasta que no se vea más!
        val bigHeartAnim3 = scaleXY(binding.imgHeart, 1.7f, 0f, 50, LinearInterpolator())
        bigHeartAnim3.startDelay = 1000

        //Agrandar
        val littleHeartAnim1 =
            scaleXY(binding.btnLikePressed, 0.5f, 0.7f, 200, LinearInterpolator())
        //Achicar un poco
        val littleHeartAnim2 = scaleXY(binding.btnLikePressed, 0.7f, 0.5f, 50, LinearInterpolator())

        bigHeartAnimatorSet.cancel()
        bigHeartAnimatorSet.apply {
            playSequentially(bigHeartAnim1, bigHeartAnim2, bigHeartAnim3)
            playSequentially(littleHeartAnim1, littleHeartAnim2)

            start()
        }

    }

    private fun animateRes() {
        val bigHeart =
            (AnimatorInflater.loadAnimator(this, R.animator.big_heart) as AnimatorSet).apply {
                setTarget(binding.imgHeart)
            }

        val littleHeart =
            (AnimatorInflater.loadAnimator(this, R.animator.little_heart) as AnimatorSet).apply {
                setTarget(binding.btnLikePressed)
            }

        bigHeartAnimatorSet.cancel()
        bigHeartAnimatorSet.apply {
            playTogether(bigHeart, littleHeart)

            start()
        }

    }

    private fun loadImages() {
        Glide.with(this)
            .load("https://instagram.faep9-1.fna.fbcdn.net/v/t51.2885-15/e35/147335359_270757987726317_6311666223172898982_n.jpg?tp=1&_nc_ht=instagram.faep9-1.fna.fbcdn.net&_nc_cat=109&_nc_ohc=ChhqlSvgqKAAX-3rFd4&edm=APU89FAAAAAA&ccb=7-4&oh=68034d501ab9ec9a01e3bf5e4f687550&oe=609A195D&_nc_sid=86f79a")
            .into(binding.imgPost)

        Glide.with(binding.imgUser)
            .load("https://instagram.faep9-2.fna.fbcdn.net/v/t51.2885-19/s320x320/149395609_415079726243423_5545141221313614036_n.jpg?tp=1&_nc_ht=instagram.faep9-2.fna.fbcdn.net&_nc_ohc=sWRSW1QWNtIAX-AEMDG&edm=ABfd0MgAAAAA&ccb=7-4&oh=c4d4f1b18a5c825ff0d45b966b935a2e&oe=609889A4&_nc_sid=7bff83")
            .circleCrop().into(binding.imgUser)
    }
}