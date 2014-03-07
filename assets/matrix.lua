lines = {}

function draw()
	i = 0
	for x = 1,screenX/10 do
		for y = 1,screenY/10 do
			g.print(randomString(), y*10, x*10,10,g.color("GREEN"))
		end
	end
end

function touch(x,y)

end

function load()
	time = os.time()
	g= graphics()
	inp = input()
	print(g.bgcolor("BLACK"))
end

function update()
	if os.time()>=time+1000 then
		time = os.time()
		--update lines
	end
	for n = 1,screenY/10 do
		lines[n] = randomString()
	end
end

function randomString() 
	char = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z","0","1","2","3","4","5","6","7","8","9", "@", "#", "$", "%", "&", "?"}
	size = screenX/10
	pass = {}
	case = math.random(1,2)
	if case == 1 then
		return string.lower(char[math.random(1,#char)])
	else
		return string.upper(char[math.random(1,#char)])
	end
end

