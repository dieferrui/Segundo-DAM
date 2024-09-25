using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Moviment : MonoBehaviour
{
    // TODO crear un menu per ajustar estes variables

    public float f_killheight = -30f;

    // Módul de la velocitat en m/s
    public float f_velocitat = 5f;

    // Velocitat de rotació
    public float f_velocitat_rotacio = 4f;

    // Velocitat de la gravetat
    Vector3 v3_g_vel_gravetat = Vector3.zero;

    // Vector velocitat normalitzat (direcció)
    Vector3 v3_loc_direccio;

    CharacterController cc_personatge;
    Vector3 v3_pos_ini = Vector3.zero;
    Quaternion q_rot_ini = Quaternion.identity;

    private void Start()
    {

        cc_personatge = GetComponent<CharacterController>();

        v3_pos_ini = transform.position;
        q_rot_ini = transform.rotation;


    }

    void Update()
    {

        if (cc_personatge.isGrounded)
        {
            
            v3_loc_direccio = Vector3.zero;

            GetComponent<Renderer>().material.color = Color.blue;

            if (Input.GetKey(KeyCode.UpArrow) || Input.GetKey(KeyCode.W))
                v3_loc_direccio += Vector3.forward;
            if (Input.GetKey(KeyCode.DownArrow) || Input.GetKey(KeyCode.S))
                v3_loc_direccio += Vector3.back;
            if (Input.GetKey(KeyCode.LeftArrow) || Input.GetKey(KeyCode.A))
                v3_loc_direccio += Vector3.left;
            if (Input.GetKey(KeyCode.RightArrow) || Input.GetKey(KeyCode.D))
                v3_loc_direccio = Vector3.right;

            if (Input.GetKey(KeyCode.Space))
            {
                v3_loc_direccio += Vector3.up;
            }

        }
        else
        {
            GetComponent<Renderer>().material.color = Color.red;
        }

        transform.Rotate(Vector3.up, f_velocitat_rotacio * Input.GetAxis("Mouse X"), Space.Self);
        Vector3 v3_glob_direccio = transform.TransformVector(v3_loc_direccio.normalized);

        // Velocitat per la acceleració de la gravetat
        v3_g_vel_gravetat += Physics.gravity * Time.deltaTime;

        if (cc_personatge.isGrounded)
            v3_g_vel_gravetat = Vector3.zero;

        Vector3 v3_g_velocitat_total = v3_g_vel_gravetat + v3_glob_direccio * f_velocitat;

        // transform.position += v3_g_velocitat_total * Time.deltaTime;
        cc_personatge.Move(v3_g_velocitat_total * Time.deltaTime);

        // Reaparició per caure
        if (transform.position.y < f_killheight)
            Respawn();

    }

    void Respawn()
    {
        transform.position = v3_pos_ini;
        transform.rotation = q_rot_ini;
        v3_g_vel_gravetat = Vector3.zero;
        v3_loc_direccio = Vector3.zero;

    }
}
