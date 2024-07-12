package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.GdxRuntimeException

// Unused
data class SmoothCircle(var x: Float, var y: Float, var bend: Float, var color: Color = Color.RED) {
    val radius = kotlin.math.abs(1 / bend)
    val center = Complex(x, y)
    val VERT_SHADER: String = """
        attribute vec2 a_position;
        uniform mat4 u_projTrans;
        uniform vec2 u_pos_center;
        varying vec4 v_pos_pixel;
        varying vec4 v_pos_center;
        void main() {
        v_pos_pixel  =  u_projTrans * vec4(a_position.xy, 0.0 , 1.0);
        v_pos_center = u_projTrans * vec4(u_pos_center.xy, 0.0 , 1.0);
        gl_Position = v_pos_pixel;
        }
        """.trimIndent()


    val FRAG_SHADER: String = """
        #ifdef GL_ES
        precision mediump float;
        #endif
        varying vec4 v_pos_pixel;
        varying vec4 v_pos_center;
        uniform mat4  u_projTrans;
        uniform vec4  u_color;
        uniform float u_radius;
        uniform float u_scale;
        void main() {
        vec4 r = u_projTrans * vec4(u_radius,0.0,0.0,0.0);
        vec4 p_pixel = v_pos_pixel,p_center = v_pos_center;
        p_pixel.x /= u_scale;
        p_center.x /= u_scale;
        float dist = distance(p_pixel.xy, p_center.xy);
        float alpha = smoothstep(r.x, r.x + 0.05, dist);
        vec4 col = mix(u_color, vec4(0.0), alpha);
        col.w = smoothstep(r.x + 0.005, r.x,dist);
        col.w *= u_color.w / 1.0;gl_FragColor = col;
        }
        """.trimIndent()

    private var mesh: Mesh
    private var shader: ShaderProgram
    private var camera: OrthographicCamera

    init {
        mesh = createMesh()
        shader = createMeshShader()

        camera = OrthographicCamera()
        camera.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    private fun createMesh(): Mesh {
        val mesh = Mesh(true, 4, 6, VertexAttribute(Usage.Position, 2, "a_position"))

        mesh.setVertices(generateVertices())
        mesh.setIndices(shortArrayOf(0, 1, 2, 1, 2, 3))

        return mesh
    }

    private fun generateVertices(): FloatArray {
        val v = FloatArray(4 * 2)

        v[0] = x - radius * 1.5f
        v[1] = y - radius * 1.5f

        v[2] = x - radius * 1.5f
        v[3] = y + radius * 1.5f

        v[4] = x + radius * 1.5f
        v[5] = y - radius * 1.5f

        v[6] = x + radius * 1.5f
        v[7] = y + radius * 1.5f

        return v
    }

    private fun createMeshShader(): ShaderProgram {
        ShaderProgram.pedantic = false
        val shader = ShaderProgram(VERT_SHADER, FRAG_SHADER)
        val log = shader.log
        if (!shader.isCompiled) throw GdxRuntimeException(log)
        if (log != null && log.length != 0) println("Shader Log: $log")
        return shader
    }

    fun dispose() {
        mesh.dispose()
        shader.dispose()
    }

    fun draw() {
        Gdx.gl.glDepthMask(false)
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        shader.bind()
        shader.setUniformMatrix("u_projTrans", camera.combined)
        shader.setUniformf("u_color", color)
        shader.setUniformf("u_scale", Gdx.graphics.height.toFloat() / Gdx.graphics.width.toFloat())
        shader.setUniformf("u_radius", radius / 1.51f)
        shader.setUniformf("u_pos_center", Vector2(x, y))
        mesh.render(shader, GL20.GL_TRIANGLES, 0, 6)
    }

    fun distance(other: SmoothCircle): Float {
        return Vector2.dst(x, y, other.x, other.y)
    }
}