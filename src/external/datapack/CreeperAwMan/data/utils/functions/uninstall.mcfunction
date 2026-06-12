tag @e remove nbs_utils
scoreboard objectives remove nbs_utils
scoreboard objectives remove nbs_utils_t
datapack disable "file/utils.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":"true"},{"text":"Data pack ","color":"yellow"},{"text":"utils.zip","color":"gold","underlined":"true"},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]