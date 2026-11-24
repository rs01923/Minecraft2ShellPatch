package me.rhys.mixin.client;

import com.mojang.blaze3d.font.TrueTypeGlyphProvider;
import org.lwjgl.util.freetype.FreeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TrueTypeGlyphProvider.class)
abstract class TrueTypeGlyphProviderMixin {
    @ModifyArg(
            method = "loadGlyph(ILorg/lwjgl/util/freetype/FT_Face;I)Lcom/mojang/blaze3d/font/UnbakedGlyph;",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/util/freetype/FreeType;FT_Load_Glyph(Lorg/lwjgl/util/freetype/FT_Face;II)I",
                    remap = false
            ),
            index = 2,
            require = 1,
            allow = 1
    )
    private int disableHints(int flags) {
        return flags | FreeType.FT_LOAD_NO_HINTING | FreeType.FT_LOAD_NO_AUTOHINT;
    }
}
