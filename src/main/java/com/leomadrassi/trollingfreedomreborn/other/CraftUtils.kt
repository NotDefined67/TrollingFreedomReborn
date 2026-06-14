package com.leomadrassi.trollingfreedomreborn.other

import org.bukkit.inventory.*
import java.util.Arrays

object CraftUtils {

    const val DATA_WILDCARD: Short = Short.MAX_VALUE

    fun areEqual(recipe1: Recipe?, recipe2: Recipe?): Boolean {
        if (recipe1 === recipe2) return true
        if (recipe1 == null || recipe2 == null) return false
        if (recipe1.result != recipe2.result) return false
        return match(recipe1, recipe2)
    }

    fun areSimilar(recipe1: Recipe?, recipe2: Recipe?): Boolean {
        if (recipe1 === recipe2) return true
        if (recipe1 == null || recipe2 == null) return false
        return match(recipe1, recipe2)
    }

    private fun match(recipe1: Recipe, recipe2: Recipe): Boolean {
        if (recipe1 is ShapedRecipe) {
            if (recipe2 !is ShapedRecipe) return false
            val r1 = recipe1
            val r2 = recipe2
            var matrix1 = shapeToMatrix(r1.shape, r1.ingredientMap)
            var matrix2 = shapeToMatrix(r2.shape, r2.ingredientMap)
            if (!Arrays.equals(matrix1, matrix2)) {
                mirrorMatrix(matrix1)
                return Arrays.equals(matrix1, matrix2)
            }
            return true
        } else if (recipe1 is ShapelessRecipe) {
            if (recipe2 !is ShapelessRecipe) return false
            val r1 = recipe1
            val r2 = recipe2
            val find = r1.ingredientList.toMutableList()
            val compare = r2.ingredientList
            if (find.size != compare.size) return false
            for (item in compare) {
                if (!find.remove(item)) return false
            }
            return find.isEmpty()
        } else if (recipe1 is FurnaceRecipe) {
            if (recipe2 !is FurnaceRecipe) return false
            val r1 = recipe1
            val r2 = recipe2
            return r1.input.type == r2.input.type
        } else {
            throw IllegalArgumentException("Unsupported recipe type: '$recipe1', update this class!")
        }
    }

    private fun shapeToMatrix(shape: Array<out String>, map: Map<Char, ItemStack>): Array<ItemStack?> {
        val matrix = arrayOfNulls<ItemStack>(9)
        var slot = 0
        for (r in shape.indices) {
            for (col in shape[r].toCharArray()) {
                matrix[slot] = map[col]
                slot++
            }
            slot = (r + 1) * 3
        }
        return matrix
    }

    private fun mirrorMatrix(matrix: Array<ItemStack?>) {
        for (r in 0..2) {
            val tmp = matrix[r * 3]
            matrix[r * 3] = matrix[r * 3 + 2]
            matrix[r * 3 + 2] = tmp
        }
    }
}
