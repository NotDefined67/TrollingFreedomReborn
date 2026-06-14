package com.leomadrassi.trollingfreedomreborn.other

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.util.Vector
import java.util.Random
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

object MathUtils : Listener {

    const val nanoToSec = 1 / 1000000000f

    const val FLOAT_ROUNDING_ERROR = 0.000001f

    const val PI = 3.141592653589793238462643383279f

    const val PI2 = PI * 2

    const val SQRT_3 = 1.73205080757f

    const val E = 2.7182818284590452354f

    const val radiansToDegrees = 180f / PI
    const val radDeg = radiansToDegrees

    const val degreesToRadians = PI / 180
    const val degRad = degreesToRadians

    private const val SIN_BITS = 14
    private const val SIN_MASK = (-1 shl SIN_BITS).inv()
    private const val SIN_COUNT = SIN_MASK + 1
    private const val degFull = 360f
    private const val radFull = PI * 2
    private const val degToIndex = SIN_COUNT / degFull
    private const val radToIndex = SIN_COUNT / radFull
    private const val ATAN2_BITS = 7
    private const val ATAN2_BITS2 = ATAN2_BITS shl 1
    private const val ATAN2_MASK = (-1 shl ATAN2_BITS2).inv()
    private const val ATAN2_COUNT = ATAN2_MASK + 1
    private val ATAN2_DIM = kotlin.math.sqrt(ATAN2_COUNT.toDouble()).toInt()
    private val INV_ATAN2_DIM_MINUS_1 = 1.0f / (ATAN2_DIM - 1)

    private const val BIG_ENOUGH_INT = 16 * 1024
    private const val BIG_ENOUGH_FLOOR = BIG_ENOUGH_INT.toDouble()
    private const val CEIL = 0.9999999
    private const val BIG_ENOUGH_CEIL = 16384.999999999996
    private const val BIG_ENOUGH_ROUND = BIG_ENOUGH_INT + 0.5f

    val random = Random()

    fun sin(radians: Float): Float {
        return Sin.table[(radians * radToIndex).toInt() and SIN_MASK]
    }

    fun cos(radians: Float): Float {
        return Sin.table[((radians + PI / 2) * radToIndex).toInt() and SIN_MASK]
    }

    fun sinDeg(degrees: Float): Float {
        return Sin.table[(degrees * degToIndex).toInt() and SIN_MASK]
    }

    fun cosDeg(degrees: Float): Float {
        return Sin.table[((degrees + 90) * degToIndex).toInt() and SIN_MASK]
    }

    fun isInteger(`object`: Any): Boolean {
        return try {
            Integer.parseInt(`object`.toString())
            true
        } catch (_: Exception) {
            false
        }
    }

    fun isDouble(`object`: Any): Boolean {
        return try {
            java.lang.Double.parseDouble(`object`.toString())
            true
        } catch (_: Exception) {
            false
        }
    }

    fun atan2(y: Float, x: Float): Float {
        var yv = y
        var xv = x
        val add: Float
        val mul: Float
        if (xv < 0) {
            if (yv < 0) {
                yv = -yv
                mul = 1f
            } else {
                mul = -1f
            }
            xv = -xv
            add = -PI
        } else {
            if (yv < 0) {
                yv = -yv
                mul = -1f
            } else {
                mul = 1f
            }
            add = 0f
        }
        val invDiv = 1 / (maxOf(xv, yv) * INV_ATAN2_DIM_MINUS_1)

        if (invDiv == Float.POSITIVE_INFINITY) {
            return (atan2(yv.toDouble(), xv.toDouble()).toFloat() + add) * mul
        }

        val xi = (xv * invDiv).toInt()
        val yi = (yv * invDiv).toInt()
        return (Atan2.table[yi * ATAN2_DIM + xi] + add) * mul
    }

    fun random(range: Int): Int {
        return random.nextInt(range + 1)
    }

    fun random(start: Int, end: Int): Int {
        return start + random.nextInt(end - start + 1)
    }

    fun randomBoolean(): Boolean {
        return random.nextBoolean()
    }

    fun randomBoolean(chance: Float): Boolean {
        return MathUtils.random() < chance
    }

    fun random(): Float {
        return random.nextFloat()
    }

    fun random(range: Float): Float {
        return random.nextFloat() * range
    }

    fun random(start: Float, end: Float): Float {
        return start + random.nextFloat() * (end - start)
    }

    fun nextPowerOfTwo(value: Int): Int {
        if (value == 0) return 1
        var v = value
        v--
        v = v or (v shr 1)
        v = v or (v shr 2)
        v = v or (v shr 4)
        v = v or (v shr 8)
        v = v or (v shr 16)
        return v + 1
    }

    fun isPowerOfTwo(value: Int): Boolean {
        return value != 0 && (value and (value - 1)) == 0
    }

    fun clamp(value: Int, min: Int, max: Int): Int {
        if (value < min) return min
        if (value > max) return max
        return value
    }

    fun clamp(value: Short, min: Short, max: Short): Short {
        if (value < min) return min
        if (value > max) return max
        return value
    }

    fun clamp(value: Float, min: Float, max: Float): Float {
        if (value < min) return min
        if (value > max) return max
        return value
    }

    fun floor(x: Float): Int {
        return (x + BIG_ENOUGH_FLOOR).toInt() - BIG_ENOUGH_INT
    }

    fun floorPositive(x: Float): Int {
        return x.toInt()
    }

    fun ceil(x: Float): Int {
        return (x + BIG_ENOUGH_CEIL).toInt() - BIG_ENOUGH_INT
    }

    fun ceilPositive(x: Float): Int {
        return (x + CEIL).toInt()
    }

    fun round(x: Float): Int {
        return (x + BIG_ENOUGH_ROUND).toInt() - BIG_ENOUGH_INT
    }

    fun roundPositive(x: Float): Int {
        return (x + 0.5f).toInt()
    }

    fun isZero(value: Float): Boolean {
        return abs(value) <= FLOAT_ROUNDING_ERROR
    }

    fun isZero(value: Float, tolerance: Float): Boolean {
        return abs(value) <= tolerance
    }

    fun isEqual(a: Float, b: Float): Boolean {
        return abs(a - b) <= FLOAT_ROUNDING_ERROR
    }

    fun isEqual(a: Float, b: Float, tolerance: Float): Boolean {
        return abs(a - b) <= tolerance
    }

    fun rotateAroundAxisX(v: Vector, angle: Double): Vector {
        val cos = cos(angle)
        val sin = sin(angle)
        val y = v.y * cos - v.z * sin
        val z = v.y * sin + v.z * cos
        return v.setY(y).setZ(z)
    }

    fun rotateAroundAxisY(v: Vector, angle: Double): Vector {
        val cos = cos(angle)
        val sin = sin(angle)
        val x = v.x * cos + v.z * sin
        val z = v.x * -sin + v.z * cos
        return v.setX(x).setZ(z)
    }

    fun rotateAroundAxisZ(v: Vector, angle: Double): Vector {
        val cos = cos(angle)
        val sin = sin(angle)
        val x = v.x * cos - v.y * sin
        val y = v.x * sin + v.y * cos
        return v.setX(x).setY(y)
    }

    fun rotateVector(v: Vector, angleX: Double, angleY: Double, angleZ: Double): Vector {
        rotateAroundAxisX(v, angleX)
        rotateAroundAxisY(v, angleY)
        rotateAroundAxisZ(v, angleZ)
        return v
    }

    fun angleToXAxis(vector: Vector): Double {
        return atan2(vector.x, vector.y)
    }

    fun getRandomVector(): Vector {
        val x = random.nextDouble() * 2.0 - 1.0
        val y = random.nextDouble() * 2.0 - 1.0
        val z = random.nextDouble() * 2.0 - 1.0
        return Vector(x, y, z).normalize()
    }

    fun applyVelocity(ent: Entity, v: Vector) {
        if (ent.hasMetadata("NPC")) return
        if (ent is Player) return
        ent.setVelocity(v)
    }

    fun applyVelocity(ent: Entity, v: Vector, ignoreGadgetsEnabled: Boolean) {
        if (ent.hasMetadata("NPC")) return
        if (!ignoreGadgetsEnabled) {
            if (ent is Player) return
        }
        ent.setVelocity(v)
    }

    fun getRandomCircleVector(): Vector {
        val rnd = random.nextDouble() * 2.0 * 3.141592653589793
        val x = cos(rnd)
        val z = sin(rnd)
        return Vector(x, 0.0, z)
    }

    fun getRandomMaterial(materials: Array<Material>): Material {
        return materials[random.nextInt(materials.size)]
    }

    fun getRandomAngle(): Double {
        return random.nextDouble() * 2 * kotlin.math.PI
    }

    fun randomDouble(min: Double, max: Double): Double {
        return if (Math.random() < 0.5) (1 - Math.random()) * (max - min) + min else Math.random() * (max - min) + min
    }

    fun randomRangeFloat(min: Float, max: Float): Float {
        return (if (Math.random() < 0.5) (1 - Math.random()) * (max - min) + min else Math.random() * (max - min) + min).toFloat()
    }

    fun randomByte(max: Int): Byte {
        return random.nextInt(max + 1).toByte()
    }

    fun randomRangeInt(min: Int, max: Int): Int {
        return (if (Math.random() < 0.5) (1 - Math.random()) * (max - min) + min else Math.random() * (max - min) + min).toInt()
    }

    fun offset(a: Entity, b: Entity): Double {
        return offset(a.location.toVector(), b.location.toVector())
    }

    fun offset(a: Location, b: Location): Double {
        return offset(a.toVector(), b.toVector())
    }

    fun offset(a: Vector, b: Vector): Double {
        return a.subtract(b).length()
    }

    private object Sin {
        val table = FloatArray(SIN_COUNT)

        init {
            for (i in 0 until SIN_COUNT) {
                table[i] = sin((i + 0.5f) / SIN_COUNT * radFull.toDouble()).toFloat()
            }
            var i = 0
            while (i < 360) {
                table[(i * degToIndex).toInt() and SIN_MASK] = sin(i * degreesToRadians.toDouble()).toFloat()
                i += 90
            }
        }
    }

    private object Atan2 {
        val table = FloatArray(ATAN2_COUNT)

        init {
            for (i in 0 until ATAN2_DIM) {
                for (j in 0 until ATAN2_DIM) {
                    val x0 = i.toFloat() / ATAN2_DIM
                    val y0 = j.toFloat() / ATAN2_DIM
                    table[j * ATAN2_DIM + i] = atan2(y0.toDouble(), x0.toDouble()).toFloat()
                }
            }
        }
    }
}
