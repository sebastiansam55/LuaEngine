function draw()
	g.rect(rectx, recty, 20,20)
end

function load()
	g = graphics()
	inp = input()
	rectx = 0
	recty = 0
end

function update()
 	ttable = inp.tilt()
 	rectx = rectx+ttable[2]
 	recty = recty-ttable[1]
end

function touch(x,y)

end