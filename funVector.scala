/**
* @author Andy Arthur
* @author Ramya Puliadi
*/

class funVector( val elems: List[Double] ) {

      def dot( rhs: funVector): Double = {
            // recursive helper
            def dot_r(rl: List[Double], ll: List[Double]): Double = {
                  (rl,ll) match {
                        case (Nil,Nil)          => 0
                        case (r :: rs, l :: ls) => (r * l) + dot_r(rs,ls)
                        case (_,_)              => 0 
                  }
            }
            dot_r(this.elems,rhs.elems)
      }

      def hadamard( rhs: funVector): funVector = {
            // recursive helper
            def had_r(rl: List[Double], ll: List[Double]): List[Double] = {
                  (rl,ll) match {
                        case (Nil,Nil)          => Nil                        
                        case (r :: rs, l :: ls) => (r * l) :: had_r(rs,ls)
                        case (_,_)              => Nil // bug: funVector length mismatch is uncaught
                  }
            }
            new funVector( had_r(this.elems, rhs.elems) )
      }

      def vecMap( f: Double => Double ): funVector = {
            // recursive helper
            def map_r( elems: List[Double], f: Double => Double): List[Double] = {
                  elems match {
                        case Nil    => Nil
                        case e::es  => f(e) :: map_r(es, f)
                  }
            }
            new funVector(map_r(this.elems, f))
      }

      def len: Int = {
            def len_r( elems: List[Double]): Int = {
                  elems match {
                        case Nil => 0
                        case e :: es => 1 + len_r(es)
                  }
            }
            len_r(elems)
      }

      override def toString() : String = "[ " + elems.mkString(" ") + " ]"
}