package org.example.Entity;

@FunctionalInterface
public interface BulletGenerator<Bullet> {
    Bullet generate();
}
