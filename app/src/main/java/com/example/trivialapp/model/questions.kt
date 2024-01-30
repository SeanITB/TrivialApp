package com.example.trivialapp.model

import com.example.trivialapp.R

// S stands for sports
// G stands for general knowledge
// H stands for history

enum class Question(
    val difficulty: Char,
    var isDone: Boolean,
    val img: Int,
    val question: String,
    val answers: Array<String>,
    val raightAnswer: String
) {
    palaMalasHierbas(
        'S',
        false,
        R.drawable.easy1,
        "Hitters use a bat while catchers use a mitt in which sport?",
        arrayOf(
        "Baseball",
        "Kendo",
        "Cricket",
        "Field hockey"),
        "Baseball"
    ),
    podadora(
        'S',
        false,
        R.drawable.easy2,
        "Players need to wear these helmets and shoulder pads for which game?",
        arrayOf(
        "Harness racing",
        "High jump",
        "Deadlifting",
        "Football"),
        "Football"
    ),
    tijerasHardineria(
        'S',
        false,
        R.drawable.easy3,
        "A cue stick needs cue chalk for which sport?",
        arrayOf(
        "Darts",
        "Billiards",
        "Croquet",
        "Polo"),
        "Billiards"
    ),
    SWIMMING_GOGGLES(
        'S',
        false,
        R.drawable.sports4,
        "For better underwater vision, this sport requires goggles. What is it?",
        arrayOf("Bodybuilding", "Crab soccer", "Swimming", "Alpine skiing"),
        "Swimming"
    ),
    BOW_ARROWS(
        'S',
        false,
        R.drawable.sportss5,
        "Bow and arrow sets will set you up for which sport?",
        arrayOf("Ballooning", "Chase tag", "Archery", "Demolition derby"),
        "Archery"
    ),
    STICK_PUCK(
        'S',
        false,
        R.drawable.sports6,
        "Having a stick and a puck means you're playing which sport?",
        arrayOf("Dodgeball", "Golf", "Hockey", "Eightball"),
        "Hockey"
    ),
    HOOPS(
        'S',
        false,
        R.drawable.sports7,
        "Hoops, there it is! What sport makes you shoot through them?",
        arrayOf("Duckpin bowling", "Billiards", "Lacrosse", "Basketball"),
        "Basketball"
    ),
    BALL_WITH_HOLES(
        'S',
        false,
        R.drawable.sports8,
        "This sport requires the ball to have holes. Guess which one!",
        arrayOf("Volleyball", "Futsal", "Bowling", "Gateball"),
        "Bowling"
    ),
    RACKET(
        'S',
        false,
        R.drawable.sports9,
        "Hitting the ball with this racket is the name of which game?",
        arrayOf("Javelin", "Pole vault", "Tennis", "Hammer throw"),
        "Tennis"
    ),
    MULTICOLORED_BELTS(
        'S',
        false,
        R.drawable.sports10,
        "Multicolored belts are more than just fashion accessories in which sport?",
        arrayOf("Gymnastics", "Hardball squash", "Karate", "Flag football"),
        "Karate"
    ),
    PADALS(
        'S',
        false,
        R.drawable.sports,
        "Great pedals can make or break a ride in which sport?",
        arrayOf("Horse polo", "Ice sledge racing", "Cycling", "Hang gliding"),
        "Cycling"
    ),
    BATON_PASSING(
        'S',
        false,
        R.drawable.sports12,
        "This track and field game type requires the passing of this baton. Know this one?",
        arrayOf("Inline skating", "Jai alai", "Kegel", "Relay racing"),
        "Relay racing"
    ),
    SHUTTLECOCK(
        'S',
        false,
        R.drawable.sports13,
        "In this sport, you let the shuttlecock fly. Familiar with this one?",
        arrayOf("Kemari", "Windsurfing", "Laser tag", "Badminton"),
        "Badminton"
    ),
    PUNCHING_BAG(
        'S',
        false,
        R.drawable.sports14,
        "Training for this sport requires this punching bag. What high stakes game is this?",
        arrayOf("Long jump", "Lumberjack", "Marathon", "Boxing"),
        "Boxing"
    ),
    SHIES(
        'S',
        false,
        R.drawable.sports15,
        "Poles are essential to pull up your lead in which sport?",
        arrayOf("Freestyle BMX", "Enduro", "Dragonboat racing", "Skiing"),
        "Skiing"
    ),
    babyGoat(
        'G',
        false,
        R.drawable.mediu1,
        "What is a baby goat called?",
        arrayOf("Cabrito", "Goti", "Small sheep", "Kid"),
        "Kid"
    ),
    hannahMontana(
        'G',
        false,
        R.drawable.midium2,
        "What was the name of Miley Cyrus's Disney character?",
        arrayOf("Jessie", "Hannah Montana", "Bella Thorne", "Cenicienta"),
        "Hannah Montana"
    ),
    brieCheese(
        'G',
        false,
        R.drawable.mediun3,
        "What country is Brie cheese originally from",
        arrayOf("Spain", "Germany", "France", "Luxembourg"),
        "France"
    ),
    nationalFlags(
        'G',
        false,
        R.drawable.difficult1,
        "What color do about 75 percent of national flags contain?",
        arrayOf("Blue", "Red", "Green", "Yellow"),
        "Red"
    ),

    erosionRock(
        'G',
        false,
        R.drawable.difficult3,
        "What is the wearing away of rock by wind, water or gravity called?",
        arrayOf("Declension", "Erosion", "Grating", "Silting"),
        "Erosion"
    ),
    vienna(
        'G',
        false,
        R.drawable.difficult4,
        "Vienna is the capital of what country?",
        arrayOf("Austria", "Germany", "Bulgaria", "Montenegro"),
        "Austria"
    ),
    earthLayers(
        'G',
        false,
        R.drawable.difficult5,
        "Which of these is NOT one of the Earth's layers?",
        arrayOf("Core", "Crust", "Mantel", "Terrestria"),
        "Terrestria"
    ),
    SEISMOLOGY(
        'G',
        false,
        R.drawable.genera_knolog8,
        "Seismology is the study of what?",
        arrayOf("Earthquakes", "Gems", "Clouds", "Extinct animals"),
        "Earthquakes"
    ),
    MARE_NOSTRUM(
        'G',
        false,
        R.drawable.general_knolege9,
        "What body of water did the Romans call MARE NOSTRUM?",
        arrayOf("The Black Sea", "The Irish Sea", "The Mediterranean Sea", "The Atlantic Ocean"),
        "The Mediterranean Sea"
    ),
    HARDEST_MINERAL(
        'G',
        false,
        R.drawable.general_knolege10,
        "Which of these is the hardest mineral on Earth?",
        arrayOf("Diamond", "Lead", "Silver", "Titanium"),
        "Diamond"
    ),
    ACTIVE_CREPUSCULAR(
        'G',
        false,
        R.drawable.general_knolege11,
        "A CREPUSCULAR animal becomes active at what time?",
        arrayOf("Dawn", "Dusk", "Midday", "Late night"),
        "Dusk"
    ),
    NASA_VIKINGS(
        'G',
        false,
        R.drawable.general_knolege12,
        "Viking I and II were spacecraft which NASA sent to",
        arrayOf("Jupiter", "Mars", "The Moon", "Orion"),
        "Mars"
    ),
    NIRVANA(
        'G',
        false,
        R.drawable.general_knolege13,
        "What religion gave us the concept of Nirvana?",
        arrayOf("Buddhism", "Jainism", "Judaism", "Christianity"),
        "Buddhism"
    ),
    CHRISTIAN_BIBLE(
        'G',
        false,
        R.drawable.general_knolege14,
        "Which of these is the last book of the Christian bible?",
        arrayOf("Obadiah", "Deuteronomy", "Revelation", "Acts of the Apostles"),
        "Revelation"
    ),
    GREEK_MYTHOLOGY(
        'G',
        false,
        R.drawable.general_knolege15,
        "Atropos, Lachesis and Clotho are the Three _____ of Greek mythology.",
        arrayOf("Fates", "Furies", "Graces", "Maenads"),
        "Fates"
    ),
    CUBA_REBOLUTION(
    'H',
            false,
        R.drawable.history1,
        "The Cuban Revolution led to the rise of what dictator?",
        arrayOf("Rafael Trujillo", "Che Guevara", "Fidel Castro", "Augusto Pinochet"),
        "Fidel Castro"
    ),
    HIROSHIMA(
        'H',
        false,
        R.drawable.history2,
        "Along with Hiroshima, Japan, which Japanese city was hit by an atomic bomb in 1945?",
        arrayOf("Tokyo", "Nagasaki", "Osaka", "Nagoya"),
        "Nagasaki"
    ),
    IRON_LADY(
        'H',
        false,
        R.drawable.history3,
        "Who was the first female prime minister of Britain?",
        arrayOf("Florence Nightingale", "Margaret Thatcher", "Queen Elizabeth II", "Jane Austen"),
        "Margaret Thatcher"
    ),
    DETH_ABRAHAM_LINCOLN(
        'H',
        false,
        R.drawable.history4,
        "Who assassinated Abraham Lincoln?",
        arrayOf("Lee Harvey Oswald", "James Earl Ray", "John Wilkes Booth", "Leon Czolgosz"),
        "John Wilkes Booth"
    ),
    ADOLF_HITLER(
        'H',
        false,
        R.drawable.history5,
        "Adolf Hitler was the leader of which party?",
        arrayOf("Communist Party", "Nazi Party", "Republican Fascist Party", "none of the above"),
        "Nazi Party"
    ),
    BLACK_DETH(
        'H',
        false,
        R.drawable.history6,
        "Which continent was devastated by the Black Death in the 1300s?",
        arrayOf("North America", "South America", "Europe", "Antartida"),
        "Europe"
    ),
    COLD_WAR(
        'H',
        false,
        R.drawable.history7,
        "The Cold War was between the United States and what other world power?",
        arrayOf("Soviet Union", "Japan", "China", "Canada"),
        "Soviet Union"
    ),
    REIGN_OF_TERROR(
        'H',
        false,
        R.drawable.hitory8,
        "The Reign of Terror was a period during which major social and political event?",
        arrayOf("American Revolution", "Cuban Revolution", "Haitian Revolution", "French Revolution"),
        "French Revolution"
    ),
    FIRST_OLYMPICS_GAMES(
        'H',
        false,
        R.drawable.history9,
        "Where were the first modern Olympics held in 1896?",
        arrayOf("France", "Italy", "Greece", "England"),
        "Greece"
    ),
    FIRST_HUMAN_INTO_SPACE(
        'H',
        false,
        R.drawable.history10,
        "Which world power sent the first human into space?",
        arrayOf("United States", "France", "China", "Soviet Union"),
        "Soviet Union"
    ),
    NAPOLEON_BONAPARTE(
        'H',
        false,
        R.drawable.history11,
        "What military leader, who later became an emperor, rose to power during the French Revolution?",
        arrayOf("Louis XVI", "Marquis de Lafayette", "Napoleon Bonaparte", "Maximilien de Robespierre"),
        "Napoleon Bonaparte"
    ),
    weimarRerpublic(
        'H',
        false,
        R.drawable.difficult2,
        "From 1919 to 1933, the Weimar Republic was the government of what nation?",
        arrayOf("Russia", "Germany", "France", "Ukraine"),
        "Germany"
    ),
    THE_GREAT_WAR(
        'H',
        false,
        R.drawable.history13,
        "In which year did World War I start?\n",
        arrayOf("1914", "1917", "1937", "1941"),
        "1914"
    ),
    PROTESTANT_REFORM(
        'H',
        false,
        R.drawable.history14,
        "The Protestant Reformation was started by whom?",
        arrayOf("John Calvin", "Francis Bacon", "Voltaire", "Martin Luther"),
        "Martin Luther"
    ),
    JULIAS_CESAR_ASSESSINATION(
        'H',
        false,
        R.drawable.history15,
        "On what date was Julius Caesar assassinated in Rome?",
        arrayOf("April 15th", "March 15th", "May 15th", "February 15th"),
        "March 15th"
    )
}