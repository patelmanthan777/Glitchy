package me.bemind.glitch

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

/**
 * Created by angelomoroni on 13/04/17.
 */

class ExtendedImageView : ImageView {

    val glithce =  Glitcher

    var effectProgress = 0

    var effectON = false

    var effect  = Effect.BASE
    set(value) {
        field = value
        invalidate()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun initEffect(effect: Effect){
        effectON = true
        glithce.initEffect((drawable as BitmapDrawable).bitmap)
        when (effect){
            Effect.ANAGLYPH -> effectProgress = 20
            else -> effectProgress = 0
        }
        this.effect = effect
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        when (effect){
            Effect.GLITCH -> Log.v("ImageView","glitch")
            Effect.ANAGLYPH  -> glithce.anaglyphCanvas(canvas,effectProgress)
            else -> Log.v("ImageView","BASE")
        }

    }

    fun save(){
        effectON = false
    }

    fun updateProgress (progress:Int){
        effectProgress = progress
        invalidate()
    }



}