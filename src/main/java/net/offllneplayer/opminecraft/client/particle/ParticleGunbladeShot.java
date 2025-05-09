
package net.offllneplayer.opminecraft.client.particle;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class ParticleGunbladeShot extends TextureSheetParticle {
	private final SpriteSet spriteSet;

	protected ParticleGunbladeShot(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(1F, 1F);
		this.quadSize = 0.7420F;
		this.lifetime = 4;
		this.gravity = 0f;
		this.hasPhysics = false;
		this.xd = vx * 0;
		this.yd = vy * 0;
		this.zd = vz * 0;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	/*
		@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			int totalFrames = 7; // Number of frames in your animation
			int frameDuration = 5; // Number of ticks each frame should last
			int currentFrame = (this.age / frameDuration) % totalFrames;
			this.setSprite(this.spriteSet.get(currentFrame, totalFrames));
		}
	}
	CUSTOM DISTRIBUTION OF THE TIME OF FRAMES, USEFUL.
*/

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			this.setSpriteFromAge(this.spriteSet);
		}
	}

	public static class ParticleGunbladeShotProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public ParticleGunbladeShotProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ParticleGunbladeShot(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	public static ParticleProvider<SimpleParticleType> provider(SpriteSet spriteSet) {
		return new ParticleGunbladeShotProvider(spriteSet);
	}
}
