####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/17/2019 18:35
####################################################################################################

execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:"squeaker"}}},tag=!effectApplied,limit=1] at @s run execute as @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] at @s run function flytre:tridents/squeaker
execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:"squeaker"}}},tag=!effectApplied,limit=1] at @s unless entity @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] run function flytre:tridents/squeaker
tag @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:"squeaker"}}},tag=!effectApplied] add effectApplied
