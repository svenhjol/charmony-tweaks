# Snapshot

- OwnableEntity::getOwnerUUID has gone. Changed to use getOwner() which returns a LivingEntity.
- TameableAnimal::setOwnerUUID has gone. Changed to use setOwner() which expects a LivingEntity.