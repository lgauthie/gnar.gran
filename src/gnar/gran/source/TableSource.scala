package gnar.gran.source

/** TableSource
 *
 *  Provides a grain source from some existing audio.
 *
 *  @constructor Creates a new grain source from an existing Array[Byte]
 *  @param table the source data to generate grains
 *
 */
class TableSource(val table:Array[Float]) extends Source{

  /** The maximum Length of the source **/
  val length = table.length

  /** synthesize
   * 
   * @param time the current time in ms
   * @param amplitude the amplitude scaler for the sample
   * @param offset the base time offset from the grain
   *
   * @return the Float for the current time scaled by the amplitude
   *
   */
  def synthesize(time:Int, amplitude:Float, offset:Int) = {
    table(time + offset) * amplitude
  }
}
