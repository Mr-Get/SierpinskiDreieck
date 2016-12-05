package at.obsidion

import android.app.Activity
import android.content.Context
import android.graphics.Path.FillType
import android.graphics.{Canvas, Paint, Path}
import android.os.Bundle
import android.view.{Display, View}

class MainActivity extends Activity {

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)
    setContentView(new CustomView(this))
  }
}

class CustomView(val context: Context) extends View(context) {
    override def onDraw(canvas: Canvas):Unit = {
      super.onDraw(canvas)
      val paint = new Paint()

      paint.setColor(android.graphics.Color.WHITE)
      canvas.drawPaint(paint)

      paint.setStrokeWidth(4)
      paint.setColor(android.graphics.Color.GREEN)
      paint.setStyle(Paint.Style.STROKE)
      //paint.setStyle(Paint.Style.FILL_AND_STROKE)
      paint.setAntiAlias(true)

      class Point(val x: Float, val y: Float)

      def Dreieck(position: Point, len: Float, check:Int): Unit = {

        if (check != 0) {
          def pythagoras(a:Float):Float = Math.sqrt((a*a)-(a/2*a/2)).toFloat

          val a: Point = new Point(position.x,position.y)
          val b: Point = new Point(position.x+len,position.y)
          val c: Point = new Point(len/2 + a.x,a.y - pythagoras(len))

          val path = new Path()
          path.setFillType(FillType.EVEN_ODD)

          path.moveTo(a.x,a.y)
          path.lineTo(b.x,b.y)
          path.lineTo(c.x,c.y)
          path.lineTo(a.x,a.y)

          path.close()
          canvas.drawPath(path,paint)

          Dreieck(a,len/2,check-1)
          val point2 = new Point(a.x+(len/4),(c.y+(a.y-c.y)/2))
          Dreieck(point2,len/2,check-1)
          val point3 = new Point(c.x,a.y)
          Dreieck(point3,len/2,check-1)

        }

      }

      val start = new Point(100,1100)
      val len: Float = 880
      val check: Int = 7

      Dreieck(start,len,check)

      /*
      val display: Display = getWindowManager().getDefaultDisplay()
      val stageWidth = display.getWidth()
      val stageHeight = display.getHeigth()
      */

    }
}




/*
@Override
protected void onDraw(Canvas canvas) {
  super.onDraw(canvas);
  Paint paint = new Paint();

  paint.setColor(android.graphics.Color.BLACK);
  canvas.drawPaint(paint);

  paint.setStrokeWidth(4);
  paint.setColor(android.graphics.Color.RED);
  paint.setStyle(Paint.Style.FILL_AND_STROKE);
  paint.setAntiAlias(true);

  Point a = new Point(0, 0);
  Point b = new Point(0, 100);
  Point c = new Point(87, 50);

  Path path = new Path();
  path.setFillType(FillType.EVEN_ODD);
  path.moveTo(a.x, a.y);
  path.lineTo(b.x, b.y);
  path.moveTo(b.x, b.y);
  path.lineTo(c.x, c.y);
  path.moveTo(c.x, c.y);
  path.lineTo(a.x, a.y);
  path.close();

  canvas.drawPath(path, paint);
}
*/