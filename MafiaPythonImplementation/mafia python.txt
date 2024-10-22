def set_default_game(players_list):
    for player in players_list:
        if player.side == 'Town' or player.name == 'Jester' or player.name == 'Mafioso':
            player.defence = 0
        elif player.name == 'Serial Killer' or player.name == 'Godfather':
            player.defence = 1

        player.targeted_by = []
        player.is_alive = True
        player.role_blocked = False


class Player:
    def __init__(self, name, number):
        self.name = name
        self.number = number


class Role:
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=[], is_alive=True,
                 role_blocked='immune', ):
        self.name = name
        self.side = side
        self.attack = attack
        self.defence = defence
        self.player = player
        self.is_sus = is_sus
        self.is_alive = is_alive
        self.role_blocked = role_blocked
        self.targeted_by = targeted_by

    def __str__(self):
        return ("Role name: " + self.name +
                "\nPlayer name: " + self.player.name +
                "\n" + self.name + " attack: " + str(self.attack) +
                "\n" + self.name + " defence: " + str(self.defence) +
                "\n" + self.name + " suspicious to sheriff: " + str(self.is_sus) +
                "\n" + self.name + " role blocked: " + str(self.role_blocked) +
                "\n" + self.name + " is targeted by: " + str(self.targeted_by) +
                "\n" + self.name + " is alive: " + str(self.is_alive))


class RoleBlockingRoles(Role):
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=None, is_alive=True,
                 role_blocked=False):
        super().__init__(name, side, attack, defence, player, is_sus, targeted_by, is_alive, role_blocked)

    def __str__(self):
        return super().__str__()

    def visit(self, target):
        if not self.role_blocked:
            if not target.role_blocked == 'immune':
                target.targeted_by.append(self)
                target.role_blocked = True


class HealingRoles(Role):
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=[], is_alive=True, role_blocked=False):
        super().__init__(name, side, attack, defence, player, is_sus, targeted_by, is_alive, role_blocked)

    def __str__(self):
        return super().__str__()

    def visit(self, target):
        if not self.role_blocked:
            target.targeted_by.append(self)
            target.defence = 2


class AttackingRoles(Role):
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=[], is_alive=True, role_blocked=False):
        super().__init__(name, side, attack, defence, player, is_sus, targeted_by, is_alive, role_blocked)

    def __str__(self):
        return super().__str__()

    def visit(self, target):
        if not self.role_blocked:
            target.targeted_by.append(self)
            if self.attack > target.defence:
                target.is_alive = False


class Godfather(AttackingRoles):
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=[], is_alive=True, role_blocked=False):
        super().__init__(name, side, attack, defence, player, is_sus, targeted_by, is_alive, role_blocked)

    def __str__(self):
        return super().__str__()

    def visit(self, target, mafioso):
        if mafioso.is_alive == True and mafioso.role_blocked == False:
            mafioso.visit(target)
        else:
            if not self.role_blocked:
                target.targeted_by.append(self)
                if self.attack > target.defence:
                    target.is_alive = False


class SerialKiller(AttackingRoles):
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=[], is_alive=True, role_blocked=False,
                 cautious=False):
        super().__init__(name, side, attack, defence, player, is_sus, targeted_by, is_alive, role_blocked)
        self.cautious = cautious

    def __str__(self):
        return super().__str__()

    def visit(self, target):
        target.targeted_by.append(self)
        if self.attack > target.defence:
            target.is_alive = False
        # if len(self.targeted_by) != 0:  #  valja napraviti da ubije samo roleBlockere
        #     if not self.cautious:
        #         for role_blockers in self.targeted_by:
        #             role_blockers.is_alive = False


class Sheriff(Role):
    def __init__(self, name, side, attack, defence, player, is_sus, targeted_by=[], is_alive=True, role_blocked=False):
        super().__init__(name, side, attack, defence, player, is_sus, targeted_by, is_alive, role_blocked)

    def visit(self, target):
        return target.is_sus


Jovan = Player('Jovan', 2)
Kole = Player('Kole', 7)
Aleksa = Player('Aleksa', 5)
Kristijan = Player('Kristijan', 6)
Knez = Player('Knez', 1)
Milica = Player('Milica', 4)
Sandra = Player('Sandra', 23)
Stefan = Player('Stefan', 8)

serial_killer = SerialKiller('Serial Killer', 'Neutral', 1, 1, Jovan, True)
jester = Role('Jester', 'Neutral', 0, 0, Kole, False)
tavern_keeper = RoleBlockingRoles('Tavern Keeper', 'Town', 0, 0, Kristijan, False)
vigilante = AttackingRoles('Vigilante', 'Town', 1, 0, Stefan, False)
doctor = HealingRoles('Doctor', 'Town', 0, 0, Aleksa, False)
godfather = Godfather('Godfather', 'Mafia', 1, 1, Knez, False)
mafioso = AttackingRoles('Mafioso', 'Mafia', 1, 0, Milica, True)
sheriff = Sheriff('Sheriff', 'Town', 0, 0, Sandra, False)

list_of_players = [serial_killer, jester, tavern_keeper, vigilante, doctor, godfather, mafioso, sheriff]

print('-------------------------------')
print(serial_killer)
print('-------------------------------')
print(jester)
print('-------------------------------')

print(serial_killer.name + " attacks " + jester.name)
serial_killer.visit(jester)
print(jester.name + " is alive: " + str(jester.is_alive))

set_default_game(list_of_players)
print("___________NEW GAME___________")

print(jester.name + " is alive: " + str(jester.is_alive))
print('-------------------------------')
print(doctor)
print('-------------------------------')

print(serial_killer.name + " attacks " + jester.name)
print(doctor.name + " heals " + jester.name)
doctor.visit(jester)
serial_killer.visit(jester)
print(jester.name + " is alive: " + str(jester.is_alive))

set_default_game(list_of_players)
print("___________NEW GAME___________")

print(tavern_keeper)
print('-------------------------------')

print(serial_killer.name + " attacks " + jester.name)
print(doctor.name + " heals " + jester.name)
print(tavern_keeper.name + " role-blocks " + doctor.name)
tavern_keeper.visit(doctor)
doctor.visit(jester)
serial_killer.visit(jester)
print(doctor.name + " is role-blocked: " + str(doctor.role_blocked))
print(jester.name + " is alive: " + str(jester.is_alive))

set_default_game(list_of_players)
print("___________NEW GAME___________")

print(godfather)
print('-------------------------------')
print(mafioso)
print('-------------------------------')

print(godfather.name + " attacks " + jester.name)
godfather.visit(jester, mafioso)
print(jester.name + " is targeted by: " + str(jester.targeted_by))
print(jester.name + " is alive: " + str(jester.is_alive))

set_default_game(list_of_players)
print("___________NEW GAME___________")

print(godfather.name + " attacks " + jester.name)
print(tavern_keeper.name + " role-blocks " + godfather.name)
tavern_keeper.visit(godfather)
godfather.visit(jester, mafioso)
print(jester.name + " is targeted by: " + str(jester.targeted_by))
print(jester.name + " is alive: " + str(jester.is_alive))

set_default_game(list_of_players)
print("___________NEW GAME___________")

print(godfather.name + " attacks " + jester.name)
print(tavern_keeper.name + " role-blocks " + mafioso.name)
tavern_keeper.visit(mafioso)
godfather.visit(jester, mafioso)
print(jester.name + " is targeted by: " + str(jester.targeted_by))
print(jester.name + " is alive: " + str(jester.is_alive))

set_default_game(list_of_players)
print("___________NEW GAME___________")

print(sheriff)
print('-------------------------------')
print(sheriff.name + " interrogates " + jester.name)
print(sheriff.visit(jester))
print(sheriff.name + " interrogates " + godfather.name)
print(sheriff.visit(godfather))
print(sheriff.name + " interrogates " + mafioso.name)
print(sheriff.visit(mafioso))