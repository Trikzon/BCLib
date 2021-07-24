package ru.bclib.client.models;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;

public class UnbakedQuad {
	float[] data = new float[20]; // 4 points with 3 positions and 2 uvs, 4 * (3 + 2)
	int spriteIndex;
	
	public void addData(int index, float value) {
		data[index] = value;
	}
	
	public void setSpriteIndex(int index) {
		spriteIndex = index;
	}
	
	public BakedQuad bake(TextureAtlasSprite[] sprites) {
		TextureAtlasSprite sprite = sprites[spriteIndex];
		int[] vertexData = new int[32];
		for (int i = 0; i < 4; i++) {
			int index = i << 3;
			int dataIndex = i * 5;
			vertexData[index] = Float.floatToIntBits(data[dataIndex++]);     // X
			vertexData[index | 1] = Float.floatToIntBits(data[dataIndex++]); // Y
			vertexData[index | 2] = Float.floatToIntBits(data[dataIndex++]); // Z
			vertexData[index | 3] = -1; // Unknown constant
			vertexData[index | 4] = Float.floatToIntBits(sprite.getU(data[dataIndex++])); // U
			vertexData[index | 5] = Float.floatToIntBits(sprite.getV(data[dataIndex]));   // V
		}
		// vertices, tint index, direction, sprite, shade
		return new BakedQuad(vertexData, 0, Direction.UP, sprites[spriteIndex], true);
	}
}
