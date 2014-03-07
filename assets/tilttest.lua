rects = {}

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
 	if rectx<0 then
 		rectx = 0
 	elseif rectx>screenX-20 then
 		rectx = screenX-20
 	else
 		rectx = rectx+ttable[2]
 	end

 	if recty<0 then
 		recty = 0
 	elseif recty>screenY-20 then
 		recty = screenY-20
 	else
 		recty = recty-ttable[1]
 	end
 	
 	


end

function touch(x,y)

end