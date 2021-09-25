package com.squaredcandy.antenna.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object AntennaColor {
    val red_200 = Color(0xffef9a9a)
    val red_600 = Color(0xffe53935)
    val red_800 = Color(0xffc62728)

    val orange_300 = Color(0xfffe7d43)

    val blood_orange_500 = Color(0xfffd5426)

    val orange_red_800 = Color(0xffd73d24)

    val green_600 = Color(0xff16d330)
    val green_800 = Color(0xff00a918)

    val amber_50 = Color(0xfffff8e1)
    val amber_100 = Color(0xffffecb3)
    val amber_200 = Color(0xffffe082)
    val amber_300 = Color(0xffffd54f)
    val amber_400 = Color(0xffffca28)
    val amber_500 = Color(0xffffc007)
    val amber_600 = Color(0xffffb300)
    val amber_700 = Color(0xffffa000)
    val amber_800 = Color(0xffff8e00)
    val amber_900 = Color(0xffff6e01)

    val blue_600 = Color(0xff1e88e5)
    val blue_700 = Color(0xff1976d2)
    val blue_800 = Color(0xff1565c0)

    val violet_200 = Color(0xff7e66ff)
    val violet_400 = Color(0xff7359fe)

    val purple_600 = Color(0xff8e24aa)
    val purple_700 = Color(0xff7b1fa2)
    val purple_800 = Color(0xff6a1b9a)

    val grey_50 = Color(0xfffafafa)
    val grey_100 = Color(0xfff5f5f5)
    val grey_200 = Color(0xffeeeeee)
    val grey_300 = Color(0xffe0e0e0)
    val grey_400 = Color(0xffbdbdbd)
    val grey_500 = Color(0xff9e9e9e)
    val grey_600 = Color(0xff757575)
    val grey_700 = Color(0xff616161)
    val grey_800 = Color(0xff424242)
    val grey_900 = Color(0xff212121)

    val grey_900_50 = Color(0x88212121)

    val white_50 = Color(0xffffffff)
    val white_100 = Color(0xffeeeeee)

    val black_700 = Color(0xff242424)
    val black_800 = Color(0xff121212)
    val black_900 = Color(0xff000000)

    val pink_200 = Color(0xffff66f8)
    val pink_300 = Color(0xfff600f7)

    val lightPalette = lightColors(
        primary = amber_700,
        primaryVariant = amber_800,
        secondary = grey_800,
        secondaryVariant = grey_900,
        background = white_50,
        surface = white_100,
        error = red_600,
        onPrimary = white_50,
        onSecondary = white_50,
        onBackground = black_900,
        onSurface = black_900,
        onError = white_50,
    )

    val darkPalette = darkColors(
        primary = amber_600,
        primaryVariant = amber_700,
        secondary = grey_600,
        background = black_800,
        surface = black_700,
        error = red_200,
        onPrimary = white_50,
        onSecondary = white_50,
        onBackground = white_50,
        onSurface = white_50,
        onError = black_900,
    )
}

val Colors.secondaryTextColor: Color get() = if (isLight) AntennaColor.grey_800 else AntennaColor.grey_600

val Colors.primaryVariantSurface: Color get() = if (isLight) primaryVariant else surface

val Colors.secondarySurface: Color get() = if (isLight) secondary else surface

val Colors.backgroundSurface: Color get() = if (isLight) background else surface
