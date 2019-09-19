####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/18/2019 07:57
####################################################################################################

execute as @a[scores={damageDealt=1..},nbt={SelectedItem:{tag:{ability:"barbed"}}}] at @s run execute as @e[nbt={HurtTime:10s},distance=..5,limit=1] at @s run effect give @s minecraft:slowness 1 1
execute as @a[scores={damageDealt=1..},nbt={SelectedItem:{tag:{ability:"barbed"}}}] at @s run execute as @e[nbt={HurtTime:10s},distance=..5,limit=1] at @s run particle cloud ~-0.1 ~0.65 ~-.1 0.2 0.75 0.2 0 35 force
scoreboard players set @a[scores={damageDealt=1..}] damageDealt 0
scoreboard players set @a[scores={killedEntity=1..}] killedEntity 0
