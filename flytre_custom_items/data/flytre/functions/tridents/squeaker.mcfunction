####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/18/2019 07:57
####################################################################################################

execute as @s run execute as @a run scoreboard players operation @s enderID4 = @s enderID
execute as @s run scoreboard players operation @a enderID4 -= @s enderID
tag @a[scores={enderID4=0}] add tri_squeaker
summon lightning_bolt
tag @a remove tri_squeaker
scoreboard players reset * enderID4
tag @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:"squeaker"}}},tag=!effectApplied,limit=1,sort=nearest] add effectApplied
execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:"squeaker"}}},tag=!effectApplied,limit=1] at @s run execute as @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] at @s run function flytre:tridents/squeaker
execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:"squeaker"}}},tag=!effectApplied,limit=1] at @s unless entity @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] run function flytre:tridents/squeaker
