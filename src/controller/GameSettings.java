package controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import utilities.Language;
/**
 * Class for set the settings of game.
 *
 */
public class GameSettings {

    private final int numberOfPlayers;
    private final int scenery;
    private final int dice;
    private static final String LANGUAGE = "LanguagesFiles.StringBundle";

    /**
     * Constructor.
     * @param numOfPlayers
     *          the number of player
     * @param scenery
     *          the scenery choose
     * @param dice
     *          the dice choose
     */
    public GameSettings(final int numOfPlayers, final int scenery, final int dice) {
        this.numberOfPlayers = numOfPlayers;
        this.scenery = scenery;
        this.dice = dice;
    }

    /**
     * Get the language choose.
     * @param language
     *          the language choose.
     * @return
     *          new map contains the language choose
     */
    public Map<String, String> getLanguage(final Language language) {
        final Map<String, String> map = new HashMap<>();
        switch (language) {
        case DE:
            final Locale localeDe = new Locale("de");
            final ResourceBundle stringsDe = ResourceBundle.getBundle(LANGUAGE, localeDe);
            final Enumeration<String> bundleKeysDe = stringsDe.getKeys();

            while (bundleKeysDe.hasMoreElements()) {
                final String key = (String) bundleKeysDe.nextElement();
                final String value = stringsDe.getString(key);
                map.put(key, value);
            }
            break;
        case ES:
            final Locale localeEs = new Locale("es");
            final ResourceBundle stringsEs = ResourceBundle.getBundle(LANGUAGE, localeEs);
            final Enumeration<String> bundleKeysEs = stringsEs.getKeys();

            while (bundleKeysEs.hasMoreElements()) {
                final String key = (String) bundleKeysEs.nextElement();
                final String value = stringsEs.getString(key);
                map.put(key, value);
            }
            break;
        case FR:
            final Locale localeFr = new Locale("fr");
            final ResourceBundle stringsFr = ResourceBundle.getBundle(LANGUAGE, localeFr);
            final Enumeration<String> bundleKeysFr = stringsFr.getKeys();

            while (bundleKeysFr.hasMoreElements()) {
                final String key = (String) bundleKeysFr.nextElement();
                final String value = stringsFr.getString(key);
                map.put(key, value);
            }
            break;
        case IT:
            final Locale localeIt = new Locale("it");
            final ResourceBundle stringsIt = ResourceBundle.getBundle(LANGUAGE, localeIt);
            final Enumeration<String> bundleKeysIt = stringsIt.getKeys();

            while (bundleKeysIt.hasMoreElements()) {
                final String key = (String) bundleKeysIt.nextElement();
                final String value = stringsIt.getString(key);
                map.put(key, value);
            }
            break;
        default:
            final Locale localeEn = new Locale("en");
            final ResourceBundle stringsEn = ResourceBundle.getBundle(LANGUAGE, localeEn);
            final Enumeration<String> bundleKeysEn = stringsEn.getKeys();

            while (bundleKeysEn.hasMoreElements()) {
                final String key = (String) bundleKeysEn.nextElement();
                final String value = stringsEn.getString(key);
                map.put(key, value);
            }
            break;
        }
        return map;
    }

    /**
     * Getter for number of players.
     * @return the number of players
     */
    public int getNumberOfPlayer() {
        return this.numberOfPlayers;
    }

    /**
     * Getter for scenery choose.
     * @return the scenery choose
     */
    public int getScenery() {
        return this.scenery;
    }

    /**
     * Getter for dice choose.
     * @return the dice choose.
     */
    public int getDice() {
        return this.dice;
    }

}
