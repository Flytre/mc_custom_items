execute as @a run scoreboard players operation @s enderID2 = @s enderID
execute as @s[type=arrow,tag=!init] at @s run scoreboard players operation @a enderID2 -= @s enderID
execute as @s[type=arrow,tag=!init] at @s if entity @a[scores={enderID2=0},nbt={SelectedItem:{tag:{ability:"explosive"}}},distance=..10] run tag @s add custom_arrow
execute as @s[type=arrow,tag=!init] at @s if entity @a[scores={enderID2=0},nbt={SelectedItem:{tag:{ability:"explosive"}}},distance=..10] run tag @s add explosive
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @s[type=arrow,tag=!init] at @s if entity @a[scores={enderID2=0},nbt={SelectedItem:{tag:{ability:"warp"}}},distance=..10] run tag @s add custom_arrow
execute as @s[type=arrow,tag=!init] at @s if entity @a[scores={enderID2=0},nbt={SelectedItem:{tag:{ability:"warp"}}},distance=..10] run tag @s add warp
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
