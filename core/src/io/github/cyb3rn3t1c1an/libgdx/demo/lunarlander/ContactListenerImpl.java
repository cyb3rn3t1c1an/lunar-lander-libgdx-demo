package io.github.cyb3rn3t1c1an.libgdx.demo.lunarlander;

import com.badlogic.gdx.physics.box2d.*;

public class ContactListenerImpl implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body landerBody = contact.getFixtureB().getBody();
        float verticalSpeed = contact.getFixtureB().getBody().getLinearVelocity().y;
        if (Math.abs(verticalSpeed) > 15) {
            ((Lander) landerBody.getUserData()).setCrashed(true);
            System.out.println("Boom!");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
