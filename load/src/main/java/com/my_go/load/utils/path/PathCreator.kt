package com.my_go.load.utils.path

import android.graphics.Path

/**
 * Create by Package com.my_go.load.utils.path
 * Created by 毛勇 on 2020/11/19
 * Current System Time 13:44
 * Describe:
 */
class PathCreator(val startX: Int, val startY: Int, val viewMargin: Int, val viewWith: Int) {

    fun createPath(type: PathType): Path {
        when (type) {
            PathType.PATH_1 -> {
                return createPath1()
            }
            PathType.LAST_PATH_1 -> {
                return createLastPath1()
            }
            PathType.PATH_2 -> {
                return createPath2()
            }
            PathType.LAST_PATH_2 -> {
                return createLastPath2()
            }
            PathType.PATH_3 -> {
                return createPath3()
            }
            PathType.LAST_PATH_3 -> {
                return createLastPath3()
            }
            PathType.PATH_4 -> {
                return createPath4()
            }
            PathType.LAST_PATH_4 -> {
                return createLastPath4()
            }
            PathType.PATH_5 -> {
                return createPath5()
            }
            PathType.LAST_PATH_5 -> {
                return createLastPath5()
            }
            PathType.PATH_6 -> {
                return createPath6()
            }
            PathType.LAST_PATH_6 -> {
                return createLastPath6()
            }
        }
    }

    private fun createLastPath6(): Path {
        var lastPath = Path()
        lastPath.moveTo(startX.toFloat() + viewMargin, startY.toFloat() - viewWith - viewMargin)
        lastPath.lineTo(startX.toFloat() + viewMargin, startY.toFloat())
        return lastPath
    }

    private fun createPath6(): Path {
        var path = Path()
        path.moveTo(
            startX.toFloat() + (viewWith + viewMargin * 2),
            startY.toFloat()
        )
        path.lineTo(
            startX.toFloat() + (viewWith + viewMargin * 2),
            startY.toFloat() - viewWith - viewMargin
        )
        path.lineTo(startX.toFloat() + viewMargin, startY.toFloat() - viewWith - viewMargin)
        return path
    }

    private fun createLastPath5(): Path {
        var lastPath = Path()
        lastPath.moveTo(
            startX.toFloat() + (viewWith + viewMargin * 2),
            startY.toFloat() + viewWith + viewMargin
        )
        lastPath.lineTo(
            startX.toFloat() + (viewWith + viewMargin * 2),
            startY.toFloat()
        )
        return lastPath
    }

    private fun createPath5(): Path {
        var path = Path()
        path.moveTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat()
        )
        path.lineTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat() + viewWith + viewMargin
        )
        path.lineTo(
            startX.toFloat() + (viewWith + viewMargin * 2),
            startY.toFloat() + viewWith + viewMargin
        )
        return path
    }

    private fun createLastPath4(): Path {
        val lastPath = Path()
        lastPath.moveTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat() - viewWith - viewMargin
        )
        lastPath.lineTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat()
        )
        return lastPath
    }

    private fun createPath4(): Path {
        val path = Path()
        path.moveTo(startX.toFloat() + (viewWith * 3 + viewMargin * 4), startY.toFloat())
        path.lineTo(
            startX.toFloat() + (viewWith * 3 + viewMargin * 4),
            startY.toFloat() - viewWith - viewMargin
        )
        path.lineTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat() - viewWith - viewMargin
        )
        return path
    }

    private fun createLastPath3(): Path {
        val lastPath = Path()
        lastPath.moveTo(
            startX.toFloat() + (viewWith * 3 + viewMargin * 4),
            startY.toFloat() + viewWith + viewMargin
        )
        lastPath.lineTo(startX.toFloat() + (viewWith * 3 + viewMargin * 4), startY.toFloat())
        return lastPath
    }

    private fun createPath3(): Path {
        val path = Path()
        path.moveTo(startX.toFloat() + (viewWith * 2 + viewMargin * 3), startY.toFloat())
        path.lineTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat() + viewWith + viewMargin
        )
        path.lineTo(
            startX.toFloat() + (viewWith * 3 + viewMargin * 4),
            startY.toFloat() + viewWith + viewMargin
        )
        return path
    }

    private fun createLastPath2(): Path {
        val lastPath = Path()
        lastPath.moveTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat() - viewWith - viewMargin
        )
        lastPath.lineTo(startX.toFloat() + (viewWith * 2 + viewMargin * 3), startY.toFloat())
        return lastPath
    }

    private fun createPath2(): Path {
        val path = Path()
        path.moveTo(
            startX.toFloat() + viewWith + viewMargin * 2,
            startY.toFloat()
        )
        path.lineTo(
            startX.toFloat() + viewWith + viewMargin * 2,
            startY.toFloat() - viewWith - viewMargin
        )
        path.lineTo(
            startX.toFloat() + (viewWith * 2 + viewMargin * 3),
            startY.toFloat() - viewWith - viewMargin
        )
        return path
    }

    private fun createLastPath1(): Path {
        val lastPath: Path = Path()
        lastPath.moveTo(
            startX.toFloat() + viewWith + viewMargin * 2,
            startY.toFloat() + viewWith + viewMargin
        )
        lastPath.lineTo(
            startX.toFloat() + viewWith + viewMargin * 2,
            startY.toFloat()
        )
        return lastPath
    }

    private fun createPath1(): Path {
        val path: Path = Path()
        path.moveTo(startX.toFloat() + viewMargin, startY.toFloat())
        path.lineTo(startX.toFloat() + viewMargin, startY.toFloat() + viewWith + viewMargin)
        path.lineTo(
            startX.toFloat() + viewWith + viewMargin * 2,
            startY.toFloat() + viewWith + viewMargin
        )
        return path
    }
}