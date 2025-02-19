package gameSection;

import pokemonSection.constants.SideOfForce;
import pokemonSection.pokedex.DataPokemonAttackClass;
import pokemonSection.pokedex.Pokemon;

import java.util.Random;

public class Turn {
    private static int turnCounter = 0;

    public static int getTurnCounter() {
        return Turn.turnCounter;
    }
    public static void setTurnCounter(int turnCounter) {
        Turn.turnCounter = turnCounter;
    }

    public static void areFitToFight(Pokemon pokemon) {
        if (pokemon.pokemonRemaingMovementsUses() <= 0 || pokemon.getHealthPoints() <= 0) pokemon.setAreInConditionToFight(false);
    }

    private static boolean isAlive(Pokemon pokemon) {
        return (pokemon.getHealthPoints() > 0);
    }

    private static Pokemon selectAWeakPokemon (Pokemon[] pokemons) {
        // Decisão vai ser entre 3 atributos, vida, velocidade e defesa, o atributo a ser utilizado como base, vai ser definido aléatoriamente:
        Random random = new Random();
        return null;
    }

    private static Pokemon selectARandomPokemon(Pokemon[] pokemons) {
        Random random = new Random();
        return pokemons[random.nextInt(pokemons.length)];
    }

    public static Pokemon selectAPokemon(Pokemon[] pokemons) {
        Pokemon targetPokemon;
        do {
            targetPokemon = Turn.selectARandomPokemon(pokemons);
        } while (!Turn.isAlive(targetPokemon));
        return targetPokemon;
    }

    public static int howManyAreInConditionToFight(Pokemon[] pokemons) {
        int inConditionToFight = 0;
        for (Pokemon pokemon : pokemons) inConditionToFight += (pokemon.getAreInConditionToFight()) ? 1 : 0;
        return inConditionToFight;
    }

    public void runTurn(Pokemon chosenPokemon, Pokemon[] heroesList, Pokemon[] villainsList) {
        if (Turn.howManyAreInConditionToFight(heroesList) <= 0) {
            Log.addResultToTurn(SideOfForce.DARK);
            Game.setGameState(false);
            return;
        } else if (Turn.howManyAreInConditionToFight(villainsList) <= 0) {
            Log.addResultToTurn(SideOfForce.LUMINOUS);
            Game.setGameState(false);
            return;
        }

        if (chosenPokemon.getAreInConditionToFight()){
            Pokemon targetPokemon;
            if (chosenPokemon.getSideOfForce() == SideOfForce.DARK) {
                targetPokemon = Turn.selectAPokemon(heroesList);
            } else {
                targetPokemon = Turn.selectAPokemon(villainsList);
            }

            DataPokemonAttackClass resultOfAttack = chosenPokemon.carryOutAttack(targetPokemon);

            // Verificar se os pokémons estão em condição de lutar.
            Turn.areFitToFight(targetPokemon);
            Turn.areFitToFight(chosenPokemon);

            Log.saveAttack(resultOfAttack, turnCounter);
            Log.addAttackToTurn();
        }
    }
}
