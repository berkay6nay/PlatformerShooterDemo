package org.example.Entity.Bullets;

@FunctionalInterface
public interface BulletGenerator<Bullet> {
    Bullet generate();
}
