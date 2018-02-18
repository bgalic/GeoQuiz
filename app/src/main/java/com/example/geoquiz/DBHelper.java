package com.example.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DBVersion = 1;
    public static final String DATABASE_NAME = "geoquiz.db";
    public static final String QUESTIONS_TABLE_NAME = "question_table";
    public static final String QUESTIONS_COL_1 = "ID";
    public static final String QUESTIONS_COL_2 = "PLACE_ID";
    public static final String QUESTIONS_COL_3 = "QUESTION";
    public static final String QUESTIONS_COL_4 = "ANSWER";

    public static final String PLACE_TABLE_NAME = "place_table";
    public static final String PLACE_COL_1 = "ID";
    public static final String PLACE_COL_2 = "LATITUDE";
    public static final String PLACE_COL_3 = "LONGITUDE";

    public static final String SCORES_TABLE_NAME = "scores_table";
    public static final String SCORES_COL_1 = "ID";
    public static final String SCORES_COL_2 = "PLACE";
    public static final String SCORES_COL_3 = "NUMBER_OF_CORRECT";
    public static final String SCORES_COL_4 = "CONTINENT";



    public static final String CREATE_TABLE_PLACE = "CREATE TABLE " + PLACE_TABLE_NAME +
            "(" + PLACE_COL_1 + " INTEGER PRIMARY KEY," + PLACE_COL_2 + " TEXT,"
            + PLACE_COL_3 + " TEXT);";

    public static final String CREATE_TABLE_SCORES = "CREATE TABLE " + SCORES_TABLE_NAME +
            "(" + SCORES_COL_1 + " INTEGER PRIMARY KEY," + SCORES_COL_2 + " TEXT,"
            + SCORES_COL_3 + " TEXT," + SCORES_COL_4 + " TEXT);";



    public static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + QUESTIONS_TABLE_NAME +
            "(" + QUESTIONS_COL_1 + " INTEGER PRIMARY KEY," + QUESTIONS_COL_2 + " TEXT,"  + QUESTIONS_COL_3 + " TEXT," +
            QUESTIONS_COL_4 + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_PLACE);
        db.execSQL(CREATE_TABLE_SCORES);

        populateQuestionTable(db);
        populatePlaceTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SCORES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PLACE_TABLE_NAME);

        onCreate(db);
    }

    private void populateQuestionTable(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(QUESTIONS_COL_2, "1");
        values.put(QUESTIONS_COL_3, "Koje je ime poznatog hokejaskog kluba iz Zagreba?");
        values.put(QUESTIONS_COL_4, "medvescak");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "1");
        values.put(QUESTIONS_COL_3, "Koje je ime poznatog parka i stadiona u Zagrebu?");
        values.put(QUESTIONS_COL_4, "maksimir");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "1");
        values.put(QUESTIONS_COL_3, "Jedna od najduzih i najpoznatijih ulica u Zagrebu(jedna rijec)?");
        values.put(QUESTIONS_COL_4, "ilica");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "2");
        values.put(QUESTIONS_COL_3, "Koji je stari rimski naziv za Osijek?");
        values.put(QUESTIONS_COL_4, "mursa");

        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "2");
        values.put(QUESTIONS_COL_3, "Koji poznati oskarovac producent dolazi iz Osijeka(Schindlerova lista, Gladijator...)?");
        values.put(QUESTIONS_COL_4, "branko lustig");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "3");
        values.put(QUESTIONS_COL_3, "Koji je car izgradio anticku palacu koja je turisticki hit u Splitu?");
        values.put(QUESTIONS_COL_4, "dioklecijan");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "4");
        values.put(QUESTIONS_COL_3, "Autooznaka grada Rijeke?");
        values.put(QUESTIONS_COL_4, "ri");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "5");
        values.put(QUESTIONS_COL_3, "Kako se zove najveca ceska rijeka koja prolazi kroz Prag?");
        values.put(QUESTIONS_COL_4, "vltava");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "5");
        values.put(QUESTIONS_COL_3, "Koji je slavni hrvatski nogometni klub osnovan u Pragu?");
        values.put(QUESTIONS_COL_4, "hajduk");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "6");
        values.put(QUESTIONS_COL_3, "Koji slavni skladatelj se rodio u Salzburgu (prezime)?");
        values.put(QUESTIONS_COL_4, "mozart");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "7");
        values.put(QUESTIONS_COL_3, "Kakve olimpijske igre su odrzane 1984. u Sarajevu?");
        values.put(QUESTIONS_COL_4, "zimske");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "8");
        values.put(QUESTIONS_COL_3, "Kako se naziva poznato setaliste u Dubrovniku?");
        values.put(QUESTIONS_COL_4, "stradun");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "9");
        values.put(QUESTIONS_COL_3, "Kako se zove glavni aerodrom u Beogradu?");
        values.put(QUESTIONS_COL_4, "nikola tesla");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "10");
        values.put(QUESTIONS_COL_3, "Kako se naziva poznata palaca u Budimpesti?");
        values.put(QUESTIONS_COL_4, "ribarska palaca");






        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "11");
        values.put(QUESTIONS_COL_3, "Kako se zove poznati festival piva koji se odrzava svake godine u Munchenu?");
        values.put(QUESTIONS_COL_4, "oktoberfest");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "12");
        values.put(QUESTIONS_COL_3, "Koja je skracenica slavnog nogometnog kluba iz Hamburga?");
        values.put(QUESTIONS_COL_4, "hsv");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "13");
        values.put(QUESTIONS_COL_3, "Koja organizacija ima sjediste u Bruxellesu?");
        values.put(QUESTIONS_COL_4, "europska unija");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "14");
        values.put(QUESTIONS_COL_3, "U kojem pariskom muzeju se cuva Da Vincijeva Mona Lisa?");
        values.put(QUESTIONS_COL_4, "louvre");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "15");
        values.put(QUESTIONS_COL_3, "Bern je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "svicarske");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "16");
        values.put(QUESTIONS_COL_3, "Kako se zove poznata katedrala u Barceloni");
        values.put(QUESTIONS_COL_4, "sagrada familia");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "17");
        values.put(QUESTIONS_COL_3, "Kako se zovu barke koje prevoze ljude po kanalima Venecije?");
        values.put(QUESTIONS_COL_4, "gondole");

        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "18");
        values.put(QUESTIONS_COL_3, "Koji je po velicini Solun grad u Grckoj? (napisati slovima)");
        values.put(QUESTIONS_COL_4, "drugi");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "19");
        values.put(QUESTIONS_COL_3, "Na kojem morskom prolazu se nalazi Istanbul?");
        values.put(QUESTIONS_COL_4, "bospor");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "20");
        values.put(QUESTIONS_COL_3, "Jeruzalem je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "izrael");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "21");
        values.put(QUESTIONS_COL_3, "Bagdad je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "irak");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "22");
        values.put(QUESTIONS_COL_3, "Koje je jezero po veličini Kaspijsko jezero? (odgovoriti slovima)");
        values.put(QUESTIONS_COL_4, "prvo");





        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "23");
        values.put(QUESTIONS_COL_3, "Koji je povijesni naziv za Iran?");
        values.put(QUESTIONS_COL_4, "perzija");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "24");
        values.put(QUESTIONS_COL_3, "Kako se zove jedno od svjetskih cuda koje se nalazi u indijskoj Agri");
        values.put(QUESTIONS_COL_4, "taj mahal");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "25");
        values.put(QUESTIONS_COL_3, "Koji je drugi naziv za drzavu Myanmar?");
        values.put(QUESTIONS_COL_4, "burma");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "26");
        values.put(QUESTIONS_COL_3, "Koja svjetska sila je u Vietnamu bezuspješno vodila rat 1968. godine?");
        values.put(QUESTIONS_COL_4, "sad");





        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "27");
        values.put(QUESTIONS_COL_3, "Kako se naziva otok kojeg dijele Indonezija, Malezija i Bruneji?");
        values.put(QUESTIONS_COL_4, "borneo");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "28");
        values.put(QUESTIONS_COL_3, "Kako se zove otok na kojem se nalazi glavni grad Indonezije Jakarta (programski jezik)?");
        values.put(QUESTIONS_COL_4, "java");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "29");
        values.put(QUESTIONS_COL_3, "Koje godine su odrzane ljetne olimpijske igre u Sydneyu?");
        values.put(QUESTIONS_COL_4, "2000.");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "30");
        values.put(QUESTIONS_COL_3, "Kako se zove veliki skup koralja na sjeveroistoku Australije?");
        values.put(QUESTIONS_COL_4, "veliki koraljni greben");





        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "31");
        values.put(QUESTIONS_COL_3, "Koji grad je najveca svjetska luka?");
        values.put(QUESTIONS_COL_4, "sangaj");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "32");
        values.put(QUESTIONS_COL_3, "Seul je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "juzne koreje");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "33");
        values.put(QUESTIONS_COL_3, "Glavni grad koje srednjoazijske drzave je Astana?");
        values.put(QUESTIONS_COL_4, "kazahstan");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "34");
        values.put(QUESTIONS_COL_3, "Koje godine je u Kyotu potpisan protokol UN-a s ciljem zastite okolisa?");
        values.put(QUESTIONS_COL_4, "2005.");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "35");
        values.put(QUESTIONS_COL_3, "Gorje Ural cini prirodnu granicu izmedju Azije i kojeg kontinenta?");
        values.put(QUESTIONS_COL_4, "europe");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "36");
        values.put(QUESTIONS_COL_3, "Kako se Sankt Peterburg zvao do 1991. godine?");
        values.put(QUESTIONS_COL_4, "lenjingrad");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "37");
        values.put(QUESTIONS_COL_3, "Helsinki je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "finske");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "38");
        values.put(QUESTIONS_COL_3, "Oresundski most-tunel povezuje gradove Malmo i koji danski grad?");
        values.put(QUESTIONS_COL_4, "kopenhagen");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "39");
        values.put(QUESTIONS_COL_3, "Koja slavna rock grupa je osnovana u Liverpool-u?");
        values.put(QUESTIONS_COL_4, "the beatles");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "40");
        values.put(QUESTIONS_COL_3, "Dublin je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "irske");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "41");
        values.put(QUESTIONS_COL_3, "Koja drzava u svome sastavu ima otok Grenland?");
        values.put(QUESTIONS_COL_4, "danska");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "42");
        values.put(QUESTIONS_COL_3, "Kojoj drzavi pripada poluotok labrador?");
        values.put(QUESTIONS_COL_4, "kanadi");






        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "43");
        values.put(QUESTIONS_COL_3, "Grad New York se u proslosti zvao New _____?");
        values.put(QUESTIONS_COL_4, "amsterdam");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "44");
        values.put(QUESTIONS_COL_3, "Na kojem poluotoku se nalazi americki grad Orlando?");
        values.put(QUESTIONS_COL_4, "florida");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "45");
        values.put(QUESTIONS_COL_3, "Ottawa je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "kanade");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "46");
        values.put(QUESTIONS_COL_3, "Beringov prolaz odvaja Sjevernu Ameriku i koji kontinent?");
        values.put(QUESTIONS_COL_4, "aziju");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "47");
        values.put(QUESTIONS_COL_3, "Kada su odrzane zimske olimpijske igre u Salt Lake City-u?");
        values.put(QUESTIONS_COL_4, "2002.");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "48");
        values.put(QUESTIONS_COL_3, "Koje je ime poznatog mosta u San Franciscu?");
        values.put(QUESTIONS_COL_4, "golden gate");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "49");
        values.put(QUESTIONS_COL_3, "Denver je glavni grad koje americke savezne drzave?");
        values.put(QUESTIONS_COL_4, "colorado");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "50");
        values.put(QUESTIONS_COL_3, "Koja poznata pjevacica dolazi s Barbadosa?");
        values.put(QUESTIONS_COL_4, "rihanna");




        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "51");
        values.put(QUESTIONS_COL_3, "Poluotok Yucatan razdvaja karipsko more i koji zaljev?");
        values.put(QUESTIONS_COL_4, "meksicki");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "52");
        values.put(QUESTIONS_COL_3, "Hispaniola je otok kojeg cine Dominikanska republika i koja jos drzava?");
        values.put(QUESTIONS_COL_4, "haiti");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "53");
        values.put(QUESTIONS_COL_3, "Caracas je glavni grad koje drzave?");
        values.put(QUESTIONS_COL_4, "venezuele");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "54");
        values.put(QUESTIONS_COL_3, "Koji je sluzbeni jezik u Surinamu?");
        values.put(QUESTIONS_COL_4, "nizozemski");






        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "55");
        values.put(QUESTIONS_COL_3, "Kako se zove glavni grad Brazila?");
        values.put(QUESTIONS_COL_4, "brasilia");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "56");
        values.put(QUESTIONS_COL_3, "Koji je prijevod grada Buenos Aires sa spanjolskog na hrvatski?");
        values.put(QUESTIONS_COL_4, "dobar zrak");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "57");
        values.put(QUESTIONS_COL_3, "U kojoj drzavi se nalazi najveca pustinja u Juznoj Americi Atacama?");
        values.put(QUESTIONS_COL_4, "cile");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "58");
        values.put(QUESTIONS_COL_3, "U kojem dijelu (strana svijeta) Juzne Amerike se nalazi pokrajina Patagonija?");
        values.put(QUESTIONS_COL_4, "jug");





        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "59");
        values.put(QUESTIONS_COL_3, "Kako se zove vrsta polumajmuna koji zivi na Madagaskaru?");
        values.put(QUESTIONS_COL_4, "lemur");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "60");
        values.put(QUESTIONS_COL_3, "Koji se poznati rt nalazi na samome jugu Afrike?");
        values.put(QUESTIONS_COL_4, "rt dobre nade");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "61");
        values.put(QUESTIONS_COL_3, "Koja pustinja se proteze uz jugozapadnu obalu Afrike?");
        values.put(QUESTIONS_COL_4, "namib");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "62");
        values.put(QUESTIONS_COL_3, "Harare je glavni grad koje juznoafricke drzave?");
        values.put(QUESTIONS_COL_4, "zimbabwe");





        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "63");
        values.put(QUESTIONS_COL_3, "U kojoj drzavi se nalazi najvisi vrh Afrike Kilimanjaro?");
        values.put(QUESTIONS_COL_4, "tanzanija");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "64");
        values.put(QUESTIONS_COL_3, "Na kojoj rijeci se nalaze Viktorijini slapovi?");
        values.put(QUESTIONS_COL_4, "zambezi");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "65");
        values.put(QUESTIONS_COL_3, "Crveno more razdvaja Afriku i koji još kontinent?");
        values.put(QUESTIONS_COL_4, "aziju");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "66");
        values.put(QUESTIONS_COL_3, "Kako se zove glavni grad Nigerije?");
        values.put(QUESTIONS_COL_4, "abuja");





        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "67");
        values.put(QUESTIONS_COL_3, "S kojim morem Sueski kanal spaja Sredozemno more?");
        values.put(QUESTIONS_COL_4, "crvenim morem");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "68");
        values.put(QUESTIONS_COL_3, "U kojoj drzavi se nalazi najvece umjetno jezero na svijetu Volta?");
        values.put(QUESTIONS_COL_4, "gana");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "69");
        values.put(QUESTIONS_COL_3, "Dakar je glavni grad koje africke drzave?");
        values.put(QUESTIONS_COL_4, "senegal");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "70");
        values.put(QUESTIONS_COL_3, "Gibraltar spaja Sredozemno more i koji ocean?");
        values.put(QUESTIONS_COL_4, "atlantski ocean");






        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "71");
        values.put(QUESTIONS_COL_3, "U kojem se oceanu nalaze Maldivi?");
        values.put(QUESTIONS_COL_4, "indijskom");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "72");
        values.put(QUESTIONS_COL_3, "Kojoj drzavi pripada Uskrsnji otok?");
        values.put(QUESTIONS_COL_4, "cile");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "73");
        values.put(QUESTIONS_COL_3, "Kojoj drzavi pripada Bozicni otok?");
        values.put(QUESTIONS_COL_4, "australija");


        values = new ContentValues();
        values.put(QUESTIONS_COL_2, "74");
        values.put(QUESTIONS_COL_3, "Kako se preziva csoba koja je otkrila Juzni pol?");
        values.put(QUESTIONS_COL_4, "amundsen");
















    }

    private void populatePlaceTable(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(PLACE_COL_2, "45.8150"); //zagreb
        values.put(PLACE_COL_3, "15.9819");


        values = new ContentValues();
        values.put(PLACE_COL_2, "45.5550"); //osijek
        values.put(PLACE_COL_3, "18.6955");


        values = new ContentValues();
        values.put(PLACE_COL_2, "43.5081"); //split
        values.put(PLACE_COL_3, "16.4402");


        values = new ContentValues();
        values.put(PLACE_COL_2, "45.3271"); //rijeka
        values.put(PLACE_COL_3, "14.4422");


        values = new ContentValues();
        values.put(PLACE_COL_2, "50.0755"); //prag
        values.put(PLACE_COL_3, "14.4378");


        values = new ContentValues();
        values.put(PLACE_COL_2, "47.8095"); //salzburg
        values.put(PLACE_COL_3, "13.0550");




        values = new ContentValues();
        values.put(PLACE_COL_2, "43.8563"); //sarajevo
        values.put(PLACE_COL_3, "18.4131");


        values = new ContentValues();
        values.put(PLACE_COL_2, "42.6507"); //Dubrovnik
        values.put(PLACE_COL_3, "18.0944");


        values = new ContentValues();
        values.put(PLACE_COL_2, "44.7866"); //beograd
        values.put(PLACE_COL_3, "20.4489");


        values = new ContentValues();
        values.put(PLACE_COL_2, "47.4979"); //budimpešta
        values.put(PLACE_COL_3, "17.3855");


        values = new ContentValues();
        values.put(PLACE_COL_2, "48.1351"); //munchen
        values.put(PLACE_COL_3, "11.5820");





        values = new ContentValues();
        values.put(PLACE_COL_2, "53.5511"); //hamburg
        values.put(PLACE_COL_3, "9.9937");


        values = new ContentValues();
        values.put(PLACE_COL_2, "50.8503"); //bruxelles
        values.put(PLACE_COL_3, "4.3517");


        values = new ContentValues();
        values.put(PLACE_COL_2, "48.8566"); //pariz
        values.put(PLACE_COL_3, "2.3522");




        values = new ContentValues();
        values.put(PLACE_COL_2, "46.9480"); // Bern
        values.put(PLACE_COL_3, "7.4474");


        values = new ContentValues();
        values.put(PLACE_COL_2, "41.3851"); // Barcelona
        values.put(PLACE_COL_3, "2.1734");


        values = new ContentValues();
        values.put(PLACE_COL_2, "45.4408"); //Venecija
        values.put(PLACE_COL_3, "12.3155");


        values = new ContentValues();
        values.put(PLACE_COL_2, "40.6401"); //Solun
        values.put(PLACE_COL_3, "22.9444");





        values = new ContentValues();
        values.put(PLACE_COL_2, "41.0082"); // Istanbul
        values.put(PLACE_COL_3, "28.9784");


        values = new ContentValues();
        values.put(PLACE_COL_2, "31.7683"); // Jeruzalem
        values.put(PLACE_COL_3, "35.2137");


        values = new ContentValues();
        values.put(PLACE_COL_2, "33.3128"); //Bagdad
        values.put(PLACE_COL_3, "44.3615");


        values = new ContentValues();
        values.put(PLACE_COL_2, "41.9350"); //Kaspijsko jezero
        values.put(PLACE_COL_3, "50.6689");





        values = new ContentValues();
        values.put(PLACE_COL_2, "35.715298"); // Teheran
        values.put(PLACE_COL_3, "51.404343");


        values = new ContentValues();
        values.put(PLACE_COL_2, "27.175015"); // Agra
        values.put(PLACE_COL_3, "78.042155");


        values = new ContentValues();
        values.put(PLACE_COL_2, "16.809141"); //Myanmar
        values.put(PLACE_COL_3, "96.156120");


        values = new ContentValues();
        values.put(PLACE_COL_2, "10.762622"); //Vietnam
        values.put(PLACE_COL_3, "106.660172");





        values = new ContentValues();
        values.put(PLACE_COL_2, "3.354681"); // Indonezija
        values.put(PLACE_COL_3, "117.596543");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-7.614529"); // Indonezija
        values.put(PLACE_COL_3, "110.71224");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-33.865143"); //Sydney
        values.put(PLACE_COL_3, "151.209900");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-18.249988"); //Australija
        values.put(PLACE_COL_3, "147.6999972");





        values = new ContentValues();
        values.put(PLACE_COL_2, "31.267401"); // Kina
        values.put(PLACE_COL_3, "121.522179");


        values = new ContentValues();
        values.put(PLACE_COL_2, "37.532600"); // Seul
        values.put(PLACE_COL_3, "127.024612");


        values = new ContentValues();
        values.put(PLACE_COL_2, "51.1605227"); //Astana
        values.put(PLACE_COL_3, "71.4703558");


        values = new ContentValues();
        values.put(PLACE_COL_2, "35.028309"); //Japan
        values.put(PLACE_COL_3, "135.753082");





        values = new ContentValues();
        values.put(PLACE_COL_2, "60.000000"); // Rusija
        values.put(PLACE_COL_3, "60.000000");


        values = new ContentValues();
        values.put(PLACE_COL_2, "59.934280"); // Sankt Petersburg
        values.put(PLACE_COL_3, "30.335099");


        values = new ContentValues();
        values.put(PLACE_COL_2, "60.192059"); //Helsinki
        values.put(PLACE_COL_3, "24.945831");


        values = new ContentValues();
        values.put(PLACE_COL_2, "55.571831"); //Oresundski most
        values.put(PLACE_COL_3, "12.822830");





        values = new ContentValues();
        values.put(PLACE_COL_2, "53.4083714"); // Liverpool
        values.put(PLACE_COL_3, "-2.9915726");


        values = new ContentValues();
        values.put(PLACE_COL_2, "53.350140"); // Dublin
        values.put(PLACE_COL_3, "-6.266155");


        values = new ContentValues();
        values.put(PLACE_COL_2, "71.706936"); //Grenland
        values.put(PLACE_COL_3, "-42.604303");


        values = new ContentValues();
        values.put(PLACE_COL_2, "53.2999988"); //Labrador
        values.put(PLACE_COL_3, "-66.5499978");





        values = new ContentValues();
        values.put(PLACE_COL_2, "40.730610"); // New York
        values.put(PLACE_COL_3, "-73.935242");


        values = new ContentValues();
        values.put(PLACE_COL_2, "28.538336"); // Orlando
        values.put(PLACE_COL_3, "-81.379234");


        values = new ContentValues();
        values.put(PLACE_COL_2, "45.425533"); // Ottawa
        values.put(PLACE_COL_3, "-75.692482");


        values = new ContentValues();
        values.put(PLACE_COL_2, "66.000000"); //Beringov prolaz
        values.put(PLACE_COL_3, "-169.000000");





        values = new ContentValues();
        values.put(PLACE_COL_2, "40.730610"); // Salt lake city
        values.put(PLACE_COL_3, "-73.935242");


        values = new ContentValues();
        values.put(PLACE_COL_2, "28.538336"); // San Francisco
        values.put(PLACE_COL_3, "-81.379234");


        values = new ContentValues();
        values.put(PLACE_COL_2, "45.425533"); // Denver
        values.put(PLACE_COL_3, "-75.692482");


        values = new ContentValues();
        values.put(PLACE_COL_2, "66.000000"); //Barbados
        values.put(PLACE_COL_3, "-169.000000");




        values = new ContentValues();
        values.put(PLACE_COL_2, "18.840329972"); // yucatan
        values.put(PLACE_COL_3, " -89.121999512");


        values = new ContentValues();
        values.put(PLACE_COL_2, "19.0"); // hispaniola
        values.put(PLACE_COL_3, "-70.666664");


        values = new ContentValues();
        values.put(PLACE_COL_2, "10.500000"); // Caracas
        values.put(PLACE_COL_3, "-66.916664");


        values = new ContentValues();
        values.put(PLACE_COL_2, "5.839398"); //Surinam
        values.put(PLACE_COL_3, "-55.199089");





        values = new ContentValues();
        values.put(PLACE_COL_2, "-15.7942287"); // Brazil
        values.put(PLACE_COL_3, "-47.8821658");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-34.603722"); // Buenos aires
        values.put(PLACE_COL_3, "-58.381592");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-23.369543"); // Atacama
        values.put(PLACE_COL_3, "-69.859741");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-41.81015"); //Patagonija
        values.put(PLACE_COL_3, "-68.90627");






        values = new ContentValues();
        values.put(PLACE_COL_2, "-19.002846"); // Madagaskar
        values.put(PLACE_COL_3, "46.460938");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-34.35483"); // Juznoafricka republika
        values.put(PLACE_COL_3, "18.46983");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-24.75116"); // Namibija
        values.put(PLACE_COL_3, "15.27249891");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-17.824858"); //Harare
        values.put(PLACE_COL_3, "31.053028");





        values = new ContentValues();
        values.put(PLACE_COL_2, "-3.065653"); // Kilimanjaro
        values.put(PLACE_COL_3, "37.352013");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-17.924444"); // Viktorijini slapovi
        values.put(PLACE_COL_3, "25.856667");


        values = new ContentValues();
        values.put(PLACE_COL_2, "20.385824"); // Crveno more
        values.put(PLACE_COL_3, "38.122559");


        values = new ContentValues();
        values.put(PLACE_COL_2, "6.465422"); //Lagos
        values.put(PLACE_COL_3, "3.406448");




        values = new ContentValues();
        values.put(PLACE_COL_2, "30.45499818"); // Sueski kanal
        values.put(PLACE_COL_3, "32.3499986");


        values = new ContentValues();
        values.put(PLACE_COL_2, "6.499998"); // Volta
        values.put(PLACE_COL_3, "0.0");


        values = new ContentValues();
        values.put(PLACE_COL_2, "14.6937"); // Dakar
        values.put(PLACE_COL_3, "-17.44406");


        values = new ContentValues();
        values.put(PLACE_COL_2, "36.1447400"); //Gibraltar
        values.put(PLACE_COL_3, "-5.3525700");






        values = new ContentValues();
        values.put(PLACE_COL_2, "1.924992"); // Maldivi
        values.put(PLACE_COL_3, "73.399658");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-27.104671"); // Uskrsnji otok
        values.put(PLACE_COL_3, "-109.360481");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-10.4912311"); // Bozicni otok
        values.put(PLACE_COL_3, "105.622981");


        values = new ContentValues();
        values.put(PLACE_COL_2, "-90.0"); //Juzni pol
        values.put(PLACE_COL_3, "0.0");



    }



    public ArrayList<Question> getQuestionsByPlaceId(int placeId) {
        ArrayList<Question> questions = new ArrayList<Question>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + QUESTIONS_TABLE_NAME + " WHERE " + QUESTIONS_COL_2 + "= " + placeId + ";";
        Question question = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                question = new Question(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                questions.add(question);
            }while (cursor.moveToNext());
        }
        db.close();
        return questions;
    }

    public ArrayList<Place> getPlaces() {
        ArrayList<Place> places = new ArrayList<Place>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PLACE_TABLE_NAME + ";";
        Place place = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                place = new Place(cursor.getInt(0),cursor.getString(1), cursor.getString(2));
                places.add(place);
            }while (cursor.moveToNext());
        }
        db.close();
        return places;
    }

    public void insertScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCORES_COL_2, score.getPlace());
        values.put(SCORES_COL_3, score.getNumberOfCorrect());
        values.put(SCORES_COL_4, score.getContinent());


        db.close();


    }



    public ArrayList<Score> getScoresByContinent(String continent) {
        ArrayList<Score> scores = new ArrayList<Score>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SCORES_TABLE_NAME + " WHERE CONTINENT='" + continent +
                "' ORDER BY CAST(" + SCORES_COL_3 +" AS INT) DESC;";
        Score score = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                score = new Score(cursor.getInt(0),cursor.getInt(2), cursor.getString(1));
                scores.add(score);
            }while (cursor.moveToNext());
        }
        db.close();
        return scores;
    }
}
