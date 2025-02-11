package pokemonSection;

import pokemonSection.constants.Type;
import pokemonSection.pokedex.DataPokemonAttackClass;
import pokemonSection.pokedex.PokeRocket;
import pokemonSection.pokedex.PokeSOX;
import pokemonSection.pokedex.Pokemon;

public class TestesParaPokemon {
    public static void testar() {
        Pokemon pokemon1 = PokeSOX.takeAPokeSOX(
                "01",
                (short) 47,
                Type.NORMAL,
                Type.FIRE);
        Pokemon pokemon2 = PokeRocket.takeAPokeRocket(
                "02",
                (short) 127,
                Type.WATER,
                Type.FIRE);

        System.out.println(pokemon1 + "\n");
        System.out.println(pokemon2 + "\n");

//        System.out.println("SEM IMUNIDADE:");
//        for (int i = 0; i < 20; i++) System.out.println(pokemon1.carryOutAttack(pokemon2) + "\n");
//        System.out.println(pokemon1 + "\n");
//        System.out.println(pokemon2 + "\n");

//        System.out.println("COM IMUNIDADE:");
//        pokemon2.getPokemonStatus().getEffects().add(StatusCondition.IMMUNITY);
//        System.out.println(pokemon1.carryOutAttack(pokemon2) + "\n");

        Pokemon[] pokemons = {pokemon1, pokemon2};
        for (int i = 0; i < 30; i++) {
            DataPokemonAttackClass resultOfAttack = pokemons[i%2].carryOutAttack(pokemons[(i+1)%2]);

            System.out.printf("Resultado do Ataque: %s\nHabilidade Utilizada: %s | Usos restantes: %s\nDano Infligido: %d\nVida do Inimigo: %d\nVida após o Ataque: %d\n\n", resultOfAttack.hitLevel, resultOfAttack.skillUsed, resultOfAttack.remainingUses, resultOfAttack.inflictedDamage, resultOfAttack.healthPointsBeforeAttack, resultOfAttack.healthPointsAfterAttack);

            if (pokemons[(i+1)%2].getHealthPoints() <= 0) {
                System.out.println("O Vencedor é o Pokémon:\n" + pokemons[i%2]);
                break;
            }
        }

        // Acessar o número de Instâncias Criadas:
        System.out.println("SOXs: " + PokeSOX.getNumberOfSOXs());
        System.out.println("Rockets: " + PokeRocket.getNumberOfRockets());
    }
}
