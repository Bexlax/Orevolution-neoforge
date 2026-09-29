package net.bexla.orevolution.content.types.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class YellowSmokeParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected YellowSmokeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z);

        this.sprites = sprites;

        this.scale(3.0F);
        this.setSize(0.25F, 0.25F);

        this.lifetime = 20;

        this.gravity = 0.12F;
        this.xd = xSpeed;
        this.yd = ySpeed - 0.03D;
        this.zd = zSpeed;

        this.friction = 0.95F;

        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime || this.alpha <= 0.0F) {
            this.remove();
            return;
        }

        this.setSpriteFromAge(this.sprites);

        this.xd += (this.random.nextDouble() - 0.5D) * 0.001D;
        this.zd += (this.random.nextDouble() - 0.5D) * 0.001D;
        this.yd -= this.gravity;

        this.move(this.xd, this.yd, this.zd);

        this.xd *= this.friction;
        this.yd *= 0.98D;
        this.zd *= this.friction;

        if (this.age >= this.lifetime - 60) {
            this.alpha = Math.max(0.0F, this.alpha - 0.015F);
        }

        if (this.onGround) {
            this.xd *= 0.7D;
            this.zd *= 0.7D;
            this.yd = 0.0D;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            YellowSmokeParticle particle = new YellowSmokeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
            particle.setAlpha(0.95F);
            return particle;
        }
    }
}