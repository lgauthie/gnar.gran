package gnar.gran.seq

import collection.mutable.ListBuffer

import gnar.gran.Audio
import gnar.gran.Grain
import gnar.gran.source._
import gnar.gran.env._

class SingleTableSequence(source:Source, sampleRate:Float, grainLengthMs:Int = 1000, mode:String = "r", poolMode:String = "s")
  extends GrainSequence(source, poolMode) {
  
  def grainLength =  (sampleRate * grainLengthMs/1000).asInstanceOf[Int]
  val env = new SinEnvelope(grainLength)
  def envelope() = env
  
  /** 
   * Plays all of the grains in the list of grains
   **/
  def playBackOrder(list:ListBuffer[Grain]) = {
    mode match{
      case "n"  => list
      case "r"  => list reverse
      case "ra" => random(list)
      case s:String =>{
        println(s + "is not a playback mode.")
        println("Playing back normal mode.")
        list
      }
    }
  }
  
  /** Used to generate the order of grains in the pool
    * 
    * 
    **/
  def poolOrder() = {
    println("this.poolMode " + this.poolMode)
    println("poolMode " + poolMode)
    println("pmode " + pmode)
    //println("super.poolMode " + super.poolMode)
    pmode match {
      case "s" => {
        val end = source.length - grainLength
        val step = grainLength
        0 until end by step/2
      }
      case "f" => {
        val end = source.length - grainLength
        val step = grainLength
        0 until end by step*2
      }
      case "n" => {
        val end = source.length - grainLength
        val step = grainLength
        0 until end by step
      }
      case "r" => {
        val end = source.length - grainLength
        val step = grainLength
        end until 0 by -step
      }
      case "rs" => {
        val end = source.length - grainLength
        val step = grainLength
        end until 0 by -step/2
      }
    }
  }

  def random(list:ListBuffer[Grain]) = {
    list
  }
}
