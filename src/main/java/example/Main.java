import entities.PetType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       /* SampleWorkingWithOwnerTable sampleWorkingWithOwnerTable = new SampleWorkingWithOwnerTable();

        // Create an item in a DB
        Owner createdOwner = sampleWorkingWithOwnerTable.createOwners(
                new Owner ("Agnese", 38, "me@home.com"));

        System.out.println(sampleWorkingWithOwnerTable.createOwners(
                new Owner ("Anita", 58, "her@home.com")));
        System.out.println(sampleWorkingWithOwnerTable.createOwners(
                new Owner ("Zane", 15, "zane@home.com")));

        System.out.println(createdOwner);

        // Get a single item from the DB
        sampleWorkingWithOwnerTable.getOwnerById(15);
        sampleWorkingWithOwnerTable.getOwnerById(createdOwner.getId());

        // Get all items from the DB
        ArrayList<Owner> owners = sampleWorkingWithOwnerTable.getAllOwners();
        System.out.println(owners);

        // Update items in DB
        Owner ownerToUpdate = sampleWorkingWithOwnerTable.createOwners(
                new Owner ("Sanda", 34, "you@home.com"));
        ownerToUpdate = owners.get(0);
        ownerToUpdate.setOwnerName("Updated Owner Name");
        ownerToUpdate.setAge(145);

        sampleWorkingWithOwnerTable.updateOwner(ownerToUpdate);
        System.out.println(
                "After Update: " +
                        sampleWorkingWithOwnerTable.getOwnerById(ownerToUpdate.getId())
        );

        // Delete an item in the DB
        Owner ownerToDelete = sampleWorkingWithOwnerTable.createOwners(
                new Owner ("Zanda", 50, "she@home.com"));
        sampleWorkingWithOwnerTable.deleteOwner(ownerToDelete.getId());
        Owner anotherOwnerToDelete = sampleWorkingWithOwnerTable.getOwnerById(createdOwner.getId());
        sampleWorkingWithOwnerTable.deleteOwner(anotherOwnerToDelete.getId());

        // After deleting, the item should not exist in the DB
        System.out.println("Found owner after deleting: " + ownerToDelete.getId() +
                sampleWorkingWithOwnerTable.getOwnerById(ownerToDelete.getId()));

*/


        SampleWorkingWithPetTypeTable sampleWorkingWithPetTypeTable = new SampleWorkingWithPetTypeTable();

        // Create an item in a DB
        PetType createdPetType = sampleWorkingWithPetTypeTable.createPetTypes(
                new PetType ("ROOSTER"));

        System.out.println(sampleWorkingWithPetTypeTable.createPetTypes(
                new PetType ("TORTOISE")));
        System.out.println(sampleWorkingWithPetTypeTable.createPetTypes(
                new PetType ("CHUPACABRA")));

        System.out.println(createdPetType);

        // Get a single item from the DB
        sampleWorkingWithPetTypeTable.getPetTypeById(3);
        sampleWorkingWithPetTypeTable.getPetTypeById(createdPetType.getId());

        // Get all items from the DB
        ArrayList<PetType> petTypes = sampleWorkingWithPetTypeTable.getAllPetTypes();
        sampleWorkingWithPetTypeTable.displayArray();

        // Update items in DB
        PetType petTypeToUpdate = sampleWorkingWithPetTypeTable.createPetTypes(
                new PetType ("PARROT"));
        petTypeToUpdate = petTypes.get(0);
        petTypeToUpdate.setType("PARAKEET");

        sampleWorkingWithPetTypeTable.updatePetType(petTypeToUpdate);
        System.out.println(
                "After Update: " +
                        sampleWorkingWithPetTypeTable.getPetTypeById(petTypeToUpdate.getId())
        );

        // Delete an item in the DB
        PetType petTypeToDelete = sampleWorkingWithPetTypeTable.createPetTypes(
                new PetType ("COBRA"));
        sampleWorkingWithPetTypeTable.deletePetType(petTypeToDelete.getId());

        // After deleting, the item should not exist in the DB
        System.out.println("Found pet type after deleting: " + petTypeToDelete.getId() +
                sampleWorkingWithPetTypeTable.getPetTypeById(petTypeToDelete.getId()));



    }
}
