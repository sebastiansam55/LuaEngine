fpstime = os.time()
frames = 0
tmpfps = 0

function load()
	g = graphics()
end
function draw()
	g.point(10,10)
	g.point(10,11, g.color("BLUE"))

	g.circle(50,100,10)
	g.circle(100,50,10, g.color("RED"))

	g.rect(150, 150, 10,10)
	g.rect(200,200,10,10, g.color("GREEN"))

	g.print("Hello world", 250, 250)
	g.print("Hello world", 250, 275, 20)
	g.print("Hello world", 250, 300,20, g.color("RED"))
	g.print("Hello world", 250, 325, 20, g.color("BLUE"), 20)

	g.line(0,0,125,150)
	g.line(0,0,150,125,g.color("RED"))


	g.circle(circlex, circley, 100)

	--draw fps, last things so always on top
	g.print(frames, 500,500)
end

function update()
	if os.time()-fpstime>=1000 then
		fpstime = os.time()
		frames = tmpfps
		tmpfps = 0
	else
		tmpfps=tmpfps+1
	end
	
end

function touch(x,y)
	circlex = x
	circley = y
end