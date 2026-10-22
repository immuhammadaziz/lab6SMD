package task10

interface PlayerCommand {
    object PLAY : PlayerCommand
    object PAUSE : PlayerCommand
    object STOP : PlayerCommand
    object MUTE : PlayerCommand
}
data class Seek(val seconds: Int) : PlayerCommand
data class Volume(val level: Int) : PlayerCommand

fun parse(line: String): PlayerCommand? {
    val parts = line.trim().lowercase().split(" ")
    val number = parts.getOrNull(1)?.toIntOrNull()
    return when (parts[0]) {
        "play" -> PlayerCommand.PLAY
        "pause" -> PlayerCommand.PAUSE
        "stop" -> PlayerCommand.STOP
        "mute" -> PlayerCommand.MUTE
        "seek" -> number?.let { Seek(it) }
        "volume" -> number?.takeIf { it in 0..100 }?.let { Volume(it) }
        else -> null
    }
}

class Player {
    private var position = 0
    private var volume = 50

    fun handle(command: PlayerCommand) {
        when (command) {
            PlayerCommand.PLAY -> println("Playing from ${position}s")
            PlayerCommand.PAUSE -> println("Paused at ${position}s")
            PlayerCommand.STOP -> {
                position = 0
                println("Stopped")
            }
            PlayerCommand.MUTE -> println("Muted")
            is Seek -> {
                position = command.seconds
                println("Jumped to ${position}s")
            }
            is Volume -> {
                volume = command.level
                println("Volume is now $volume%")
            }
        }
    }
}

fun main() {
    val player = Player()
    println("Commands: play, pause, stop, mute, seek N, volume N (0-100), quit")
    while (true) {
        print("> ")
        val line = readln()
        if (line.trim() == "quit") break
        val command = parse(line)
        if (command == null) println("Unknown command") else player.handle(command)
    }
}
