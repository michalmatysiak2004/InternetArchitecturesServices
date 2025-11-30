import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditClub } from './edit-club';

describe('EditClub', () => {
  let component: EditClub;
  let fixture: ComponentFixture<EditClub>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditClub]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditClub);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
